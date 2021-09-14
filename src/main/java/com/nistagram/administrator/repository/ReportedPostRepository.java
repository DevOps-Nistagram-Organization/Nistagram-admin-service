package com.nistagram.administrator.repository;

import com.nistagram.administrator.model.entity.ReportedPost;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ReportedPostRepository extends JpaRepository<ReportedPost, Long> {
    List<ReportedPost> findAllByPostId(Long postId);
    @Transactional
    void deleteAllByPostId(Long postId);
}
