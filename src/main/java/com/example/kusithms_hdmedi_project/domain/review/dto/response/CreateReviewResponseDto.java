package com.example.kusithms_hdmedi_project.domain.review.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateReviewResponseDto {

    private Long reviewId;

    public static CreateReviewResponseDto of(Long reviewId){
        return CreateReviewResponseDto.builder()
                .reviewId(reviewId)
                .build();
    }
}
