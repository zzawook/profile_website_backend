package dev.kjaehyeok21.profile_website.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.kjaehyeok21.profile_website.entities.BlogPost;
import dev.kjaehyeok21.profile_website.mappers.BlogPostMapper;
import dev.kjaehyeok21.profile_website.models.GetBlogPostHolder;
import dev.kjaehyeok21.profile_website.repositories.BlogPostRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BlogPostServiceImpl implements BlogPostService{

    final BlogPostRepository blogPostRepository;

    final BlogPostMapper blogPostMapper;

    final AwsSecretsManagerService awsSecretsManagerService;

    String user_id, api_key;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, BlogPostMapper blogPostMapper,
            AwsSecretsManagerService awsSecretsManagerService) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostMapper = blogPostMapper;
        this.awsSecretsManagerService = awsSecretsManagerService;

        this.fetchMediumAPISecrets();
        this.fetchBlogPostData();
    }

    private void fetchMediumAPISecrets() {
        String temp_user_id = "", temp_api_key = "";
        try {
            temp_user_id = awsSecretsManagerService.getSecret("medium_api_userid");
            temp_api_key = awsSecretsManagerService.getSecret("medium_api_key");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fetching Medium API Key");
            return;
        }

        if (temp_api_key.length() > 0 && temp_user_id.length() > 0) {
            this.user_id = temp_user_id;
            this.api_key = temp_api_key;
        }
    }

    private boolean isAPISecretsValid() {
        return this.user_id.length() > 0 && this.api_key.length() > 0;
    }

    @Scheduled(cron = "0 0 4 * * ?")
    private void fetchBlogPostDataDaily() {
        this.fetchBlogPostData();
    }

    private void fetchBlogPostData() {
        if (!this.isAPISecretsValid()) {
            System.out.println("Medium API Secrets are not valid, skipping blog post data fetch");
            return;
        }

        WebClient webClient = WebClient.create();
        webClient.get()
                .uri("https://medium2.p.rapidapi.com/user/" + this.user_id + "/articles")
                .header("x-rapidapi-key", this.api_key)
                .header("x-rapidapi-host", "medium2.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    JSONObject jsonArray = new JSONObject(str);
                    JSONArray articles = jsonArray.getJSONArray("associated_articles");
                    List<String> articleIds = new ArrayList<String>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        String articleId = articles.getString(i);
                        articleIds.add(articleId);
                    }
                    ;

                    return articleIds;
                })
                .subscribe(articleIds -> {
                    for (int i = 0; i < articleIds.size(); i++) {
                        String articleId = articleIds.get(i);
                        this.blogPostRepository.findByMediumId(articleId).doOnSuccess(blogPost -> {
                            if (blogPost == null) {
                                this.getBlogPostFromMedium(articleId).doOnNext(fetchedBlogPost -> {
                                    blogPostRepository.save(fetchedBlogPost).subscribe();
                                }).subscribe();
                            }
                        }).subscribe();
                    }
                });
    }

    private Mono<BlogPost> getBlogPostFromMedium(String articleId) {
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri("https://medium2.p.rapidapi.com/article/" + articleId)
                .header("x-rapidapi-key", this.api_key)
                .header("x-rapidapi-host", "medium2.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    JSONObject article = new JSONObject(str);
                    String title = article.getString("title");
                    String subtitle = article.getString("subtitle");
                    String url = article.getString("url");
                    String createdAt = article.getString("published_at");
                    String updatedAt = article.getString("last_modified_at");

                    LocalDateTime createdDT = null, updatedDT = null;

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        createdDT = sdf.parse(createdAt).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        updatedDT = sdf.parse(updatedAt).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("Blog Article Date Parse Exception");
                    }

                    BlogPost blogPost = BlogPost.builder()
                            .mediumId(articleId)
                            .title(title)
                            .subtitle(subtitle)
                            .createdAt(createdDT)
                            .updatedAt(updatedDT)
                            .url(url)
                            .build();

                    return blogPost;
                });
    }

    @Override
    public Flux<GetBlogPostHolder> getBlogPosts() {
        return blogPostRepository.findAll().map(blogPost -> blogPostMapper.blogPostToGetBlogHolder(blogPost));
    }
}
