package com.example.kusithms_hdmedi_project.domain.review.service;

import com.example.kusithms_hdmedi_project.domain.review.repository.VReviewRepository;
import com.example.kusithms_hdmedi_project.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final VReviewRepository verifiedReviewRepository;

}
