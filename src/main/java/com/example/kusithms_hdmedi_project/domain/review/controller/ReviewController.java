package com.example.kusithms_hdmedi_project.domain.review.controller;


import com.example.kusithms_hdmedi_project.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@Controller
public class ReviewController {

    private final ReviewService reviewService;

}
