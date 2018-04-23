package com.sharingsession.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharingsession.model.Comment;

 
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
