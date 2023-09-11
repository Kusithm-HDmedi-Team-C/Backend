package com.example.kusithms_hdmedi_project.domain.review.repository;

import com.example.kusithms_hdmedi_project.domain.review.entity.VerifiedReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifiedReviewRepository extends JpaRepository<VerifiedReview, Long> {
}
