package com.example.kusithms_hdmedi_project.domain.review.repository;

import com.example.kusithms_hdmedi_project.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
