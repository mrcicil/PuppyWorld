package com.example.ad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ad.domain.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {

}
