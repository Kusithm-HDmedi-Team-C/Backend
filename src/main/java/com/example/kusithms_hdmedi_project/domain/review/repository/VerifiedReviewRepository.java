package com.example.kusithms_hdmedi_project.domain.review.repository;

import com.example.kusithms_hdmedi_project.domain.review.entity.VerifiedReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface VerifiedReviewRepository extends JpaRepository<VerifiedReview, Long> {

    Page<VerifiedReview> findByHospitalIdOrderByCreateDateDesc(Long hospitalId, Pageable pageable);

    List<VerifiedReview> findByHospitalIdOrderByCreateDateDesc(Long hospitalId);
}
