package org.fbertos.services.users;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.mock.web.MockMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Rule;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
	
	@Autowired
    private WebApplicationContext context;
	
    private MockMvc mockMvc;
	
    @MockBean
    private MongoService mongoService;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }
    
    @Test
    public void testGet() throws Exception {
        this.mockMvc.perform(get("/read").header("Authorization", "3423fdsfddd432434dffs20018!!"))
            .andExpect(status().isOk())
            .andDo(document("users/read",
            		responseFields(fieldWithPath("id").description("Generated id"),
            				fieldWithPath("name").description("User name"))
            		));
    }
    
    @Test
    public void testCreate() throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
    	User u = new User("admin");
    	String body = mapper.writeValueAsString(u);

        this.mockMvc.perform(post("/create")
        		.header("Authorization", "3423fdsfddd432434dffs20018!!")
        		.header("Content-Type", "application/json")
        		.accept(MediaType.APPLICATION_JSON)
        		.content(body))
            .andExpect(status().isOk())
            .andDo(document("users/create",
            		responseFields(fieldWithPath("id").description("Generated id"),
            				fieldWithPath("name").description("User name"))
            		));
    }

    @Test
    public void testFile() throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
    	User u = new User("admin");
    	String body = mapper.writeValueAsString(u);

        MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        MockMultipartFile user = new MockMultipartFile("user", "", "application/json", body.getBytes());

        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/file")
        		.file(user)
        		.file(file)
        		.header("Authorization", "3423fdsfddd432434dffs20018!!")
        		.header("Content-Type", "application/json")
        		.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("users/file",
            		responseFields(fieldWithPath("id").description("Generated id"),
            				fieldWithPath("name").description("User name"))
            		));
    }
}

