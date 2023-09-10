package com.example.kusithms_hdmedi_project.domain.review.dto.response;

import lombok.Builder;

@Builder
public class VerifyReviewResponseDto {

    private Long verifiedReviewId;

    public static VerifyReviewResponseDto of(Long verifiedReviewId) {
        return VerifyReviewResponseDto.builder()
                .verifiedReviewId(verifiedReviewId)
                .build();
    }
}
