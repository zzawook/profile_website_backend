package dev.kjaehyeok21.profile_website.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.kjaehyeok21.profile_website.controllers.ResumeController;

@WebMvcTest(ResumeController.class)
public class ResumeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetResume() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/resume"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("resume"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("resume"));
    }
}