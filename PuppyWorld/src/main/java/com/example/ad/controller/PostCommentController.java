package com.example.ad.controller;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ad.domain.Post;
import com.example.ad.domain.PostComment;
import com.example.ad.domain.Product;
import com.example.ad.domain.Services;
import com.example.ad.domain.User;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.service.PostCommentService;
import com.example.ad.service.PostCommentServiceImplementation;
import com.example.ad.service.PostService;
import com.example.ad.service.PostServiceImplementation;
import com.example.ad.service.ProductService;
import com.example.ad.service.ProductServiceImplementation;
import com.example.ad.service.ServiceService;
import com.example.ad.service.ServiceServiceImplementation;
import com.example.ad.service.UserService;
import com.example.ad.service.UserServiceImplementation;

@Controller
public class PostCommentController {
	
	@Autowired
	private PostService poservice;
	
	@Autowired
	private PostCommentService pcservice;
	
	@Autowired
	public void setPCService(PostCommentServiceImplementation pcServiceImpl) {
		this.pcservice = pcServiceImpl;
	}
	
	@Autowired
	public void setPoService(PostServiceImplementation poServiceImpl) {
		this.poservice = poServiceImpl;
	}
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	public void setUService(UserServiceImplementation uServiceImpl) {
		this.uservice = uServiceImpl;
	}
	
	
	

	
	@RequestMapping(value="/postCommentSave",method=RequestMethod.POST)
	public String savePost(@ModelAttribute("postcomment")PostComment postcomment, Model model, Errors errors, BindingResult bindingResult, HttpServletRequest request)  {
		
		Post linkedpost = poservice.findPostById(postcomment.getPost().getPostId());
		
		User user = uservice.findUserByUserName(request.getRemoteUser());
		postcomment.setUser(user);
		postcomment.setPost(linkedpost);
		postcomment.setCommentDateTime(LocalDateTime.now());

		int postid = linkedpost.getPostId();
		model.addAttribute("postcomment",postcomment);
		
		if(postcomment.getCommentMessage().isEmpty()) {
			errors.rejectValue("postCommentMessage", "null", "Must be filled");
		}
		
		if (bindingResult.hasErrors()) {
			return "forward:/viewPostDetails/"+postid;
		}
		
		pcservice.savePostComment(postcomment);
		
		return "forward:/viewPostDetails/"+postid;

	}
	

	
	
	
	@RequestMapping(value = "/postCommentDelete/{commentId}")
	public String deletePostComment(@PathVariable("commentId") int commentId, Model model, HttpServletRequest request) {
		PostComment currentcomment = pcservice.findPostCommentById(commentId);
		Post post = currentcomment.getPost();
		model.addAttribute("post", post);
		int postid = post.getPostId();
		
		pcservice.deletePostCommentById(commentId);
		return "forward:/viewPostDetails/"+postid;
	}
	
	
	@RequestMapping(value = "/postCommentEdit/{commentId}")
	public String editPostComment(@PathVariable("commentId") int commentId, Model model, HttpServletRequest request) {
		PostComment currentcomment = pcservice.findPostCommentById(commentId);
		Post post = currentcomment.getPost();
		model.addAttribute("post", post);
		model.addAttribute("postcomment", currentcomment);
		User user = uservice.findUserByUserName(request.getRemoteUser());
		model.addAttribute("user", user);
		List<PostComment> postcommentlist = pcservice.findPostCommentsbyPostId(post.getPostId());
		model.addAttribute("postcommentlist", postcommentlist);
		
			String encodedString = Base64.getEncoder().encodeToString(post.getPostImage());
			PostComment postcomment = new PostComment();
			postcomment.setPost(post);
			model.addAttribute("image", encodedString);
					
		return "postCommentEdit";
	}
	
	@RequestMapping(value="/postCommentEditSave",method=RequestMethod.POST)
	public String saveeditedPost(@ModelAttribute("postcomment")PostComment postcomment, Model model, Errors errors, BindingResult bindingResult, HttpServletRequest request)  {
		
		Post linkedpost = poservice.findPostById(postcomment.getPost().getPostId());
		
		User user = uservice.findUserByUserName(request.getRemoteUser());

		int postid = linkedpost.getPostId();
		
		if(postcomment.getCommentMessage().isEmpty()) {
			errors.rejectValue("postCommentMessage", "null", "Must be filled");
		}
		
		if (bindingResult.hasErrors()) {
			return "forward:/viewPostDetails/"+postid;
		}
		
		pcservice.savePostComment(postcomment);
		
		return "forward:/viewPostDetails/"+postid;

	}
	
}