package com.example.kusithms_hdmedi_project.domain.review.controller;


import com.example.kusithms_hdmedi_project.domain.review.dto.request.CreateReviewRequestDto;
import com.example.kusithms_hdmedi_project.domain.review.dto.response.CreateReviewResponseDto;
import com.example.kusithms_hdmedi_project.domain.review.dto.response.GetHospitalVReviewResponseDto;
import com.example.kusithms_hdmedi_project.domain.review.dto.response.VerifyReviewResponseDto;
import com.example.kusithms_hdmedi_project.domain.review.service.ReviewService;
import com.example.kusithms_hdmedi_project.global.common.BaseResponse;
import com.example.kusithms_hdmedi_project.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{hospitalId}")
    public ResponseEntity<BaseResponse<?>> getHospitalVerifiedReview(@PathVariable Long hospitalId) {
        List<GetHospitalVReviewResponseDto> getHospitalVReviewResponseDto = reviewService.getHospitalVerifiedReview(hospitalId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, getHospitalVReviewResponseDto));
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<?>> createHospitalReview(@RequestPart CreateReviewRequestDto createReviewRequestDto,
                                                                @RequestPart MultipartFile file) throws IOException {
        CreateReviewResponseDto createReviewResponseDto = reviewService.createReview(createReviewRequestDto, file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, createReviewResponseDto));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<BaseResponse<?>> verifyReview(@PathVariable Long reviewId) {
        VerifyReviewResponseDto verifyReviewResponseDto = reviewService.verifyReview(reviewId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, verifyReviewResponseDto));
    }
}
