FROM nginx

# Copy nginx config file, initialization script
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80
EXPOSE 443
EXPOSE 22

CMD ["nginx", "-g", "daemon off;"]