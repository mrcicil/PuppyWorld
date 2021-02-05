package com.example.ad.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ad.domain.PostComment;
import com.example.ad.repo.PostCommentRepository;

public class PostCommentServiceImplementation implements PostCommentService {
	
	@Autowired
	PostCommentRepository pcrepo;

	@Override
	public void savePostComment(PostComment postComment) {
		// TODO Auto-generated method stub
		pcrepo.save(postComment);
	}

	@Override
	public ArrayList<PostComment> findAllPostComments() {
		// TODO Auto-generated method stub
		return (ArrayList<PostComment>) pcrepo.findAll();
	}

	@Override
	public PostComment findPostCommentById(int Id) {
		// TODO Auto-generated method stub
		ArrayList<PostComment> pcList = findAllPostComments();
		PostComment searchPostComment = null;
		for (Iterator <PostComment>iterator = pcList.iterator(); iterator.hasNext();) {
			PostComment postComment = iterator.next();
			if(postComment.getCommentId() == Id) {
				searchPostComment = postComment;
			}	
		}
		return searchPostComment;
	}

	@Override
	public void deleteEventById(int Id) {
		// TODO Auto-generated method stub
		PostComment searchPostComment = findPostCommentById(Id);
		pcrepo.delete(searchPostComment);
		
	}

}
