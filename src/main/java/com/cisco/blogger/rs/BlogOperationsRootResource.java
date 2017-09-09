package com.cisco.blogger.rs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.BlogCreateException;
import com.cisco.blogger.api.BlogException;
import com.cisco.blogger.api.BlogUpdateException;
import com.cisco.blogger.api.Comment;
import com.cisco.blogger.api.User;
import com.cisco.blogger.service.BlogService;
import com.cisco.blogger.service.UserService;
@Component
@Path("/blog")
public class BlogOperationsRootResource {
	
	BlogService blogService;
	UserService userService;
	
	@Autowired
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}


	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(Blog blog) {
		try {
			
			String blogId = blogService.createBlog(blog);

			return Response.ok().entity(blog).header("location", "/blogger/blog/view/" + blogId).build();

		} catch (BlogCreateException bce) {
			return Response.status(400).build();
		} catch (BlogException be) {
			return Response.status(500).build();
		}

	}

	@POST
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(@PathParam("id") String id, Blog blog) {
		try {
			 blogService.updateBlog(blog);
			
			return Response.ok().entity(blog).header("location", "/blogger/blog/view/" +id)
					.build();
		} catch (BlogUpdateException bue) {
			return Response.status(400).build();
		} catch (BlogException be) {
			return Response.status(500).build();
		}

	}

	@GET
	@Path("/view/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getBlogById(@PathParam("id") String id) {
		try {
			Blog blog = blogService.viewBlog(id);

			return Response.ok().entity(blog).build();

		} catch (BlogCreateException bce) {
			return Response.status(400).build();
		} catch (BlogException be) {
			return Response.status(500).build();
		}

	}

	@GET
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response listAllBlogs() {
		try {
			List<Blog> blogs = blogService.listAllBlogs();

			return Response.ok().entity(blogs).build();

		} catch (BlogCreateException bce) {
			return Response.status(400).build();
		} catch (BlogException be) {
			return Response.status(500).build();
		}

	}

	@GET
	@Path("/{keyWord}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchBlog(@PathParam("keyWord") String keyWord) {
		try {
			List<Blog> blogs = blogService.searchBlogs(keyWord);

			return Response.ok().entity(blogs).build();

		} catch (BlogCreateException bce) {
			return Response.status(400).build();
		} catch (BlogException be) {
			return Response.status(500).build();
		}

	}

	@POST
	@Path("/comment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addComment(Blog blog) {
		try {

			blogService.addComment(blog);

			return Response.status(200).build();

		} catch (BlogCreateException bce) {
			bce.printStackTrace();
			return Response.status(400).build();
		} catch (BlogException be) {
			return Response.status(500).build();
		}

	}
	

}
