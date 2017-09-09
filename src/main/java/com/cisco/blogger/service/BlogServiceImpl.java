package com.cisco.blogger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.Comment;
import com.cisco.blogger.data.BlogRepository;
@Service
public class BlogServiceImpl implements BlogService {
	
	private BlogRepository blogRepository;
	 @Autowired
	  public void setBlogRepository(BlogRepository blogRepository) {
	        this.blogRepository = blogRepository;
	    }
	


	public String createBlog(Blog blog) {

	Blog newBlog = this.blogRepository.insert(blog);
	return newBlog.getId();

	}

	public void updateBlog(Blog blog) {
		
		this.blogRepository.save(blog);
	}

	public List<Blog> searchBlogs(String keyword) {
	//	return blogRepository.findByKeyword(keyword);
		return null;
	}

	public Blog viewBlog(String blogId) {
		return this.blogRepository.findById(blogId);
	}

	public List<Blog> listAllBlogs() {

		return this.blogRepository.findAll();
	}

	public void addComment(Blog blog) {

		this.blogRepository.save(blog);
	}



	

	

}
