package com.cisco.blogger.rs;

import java.util.Arrays;
import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.Comment;
import com.cisco.blogger.api.User;
import com.cisco.blogger.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(BlogOperationsRootResource.class)
public class TestBlogOperationsRootResource {

	 @Autowired
	    MockMvc mockMvc;
	  @MockBean
	    BlogService blogServiceMock;
	  
	  @Autowired
	    ObjectMapper objectMapper;
	  
	  @Test
	    public void testCreateBlogSuccessfully() throws Exception {
		  
		  Blog blog = new Blog("My first blog",new Date(),new Date(), "This is an autogenrated CMAD blog for Demo purpose.",
					0,new User(),Arrays.asList(new Comment("My first comment"),new Comment("My 2nd comment"),new Comment("My 3rd comment")));
		  blog.setId("XYZ");
			
	        Mockito.when(blogServiceMock.createBlog(blog)).thenReturn(blog.getId());
	        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("blogger/blogger-world/user").accept(
					MediaType.APPLICATION_JSON);
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        System.out.println(result.getResponse());
	        
	        String expected = "{id:\"XYZ\"}";
	        JSONAssert.assertEquals(expected, result.getResponse()
					.getContentAsString(), false);
	    }


}
