package com.example.kusithms_hdmedi_project.domain.review.dto.response;

import com.example.kusithms_hdmedi_project.domain.review.entity.ExaminationType;
import com.example.kusithms_hdmedi_project.domain.review.entity.VerifiedReview;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class GetHospitalVReviewResponseDto {

    private Long reviewId;

    private Integer rating;

    private String content;

    private String doctor;

    private Integer price;

    private List<ExaminationType> examinations;

    private LocalDateTime createdAt;


    public static GetHospitalVReviewResponseDto of(VerifiedReview verifiedReview) {

        return GetHospitalVReviewResponseDto.builder()
                .reviewId(verifiedReview.getId())
                .rating(verifiedReview.getRating())
                .content(verifiedReview.getContent())
                .doctor(verifiedReview.getDoctor())
                .price(verifiedReview.getPrice())
                .examinations(
                        verifiedReview.getReviewExaminations()
                        .stream()
                        .map(ExaminationType::of)
                        .collect(Collectors.toList()))
                .createdAt(verifiedReview.getCreateDate())
                .build();

    }
}
