package com.example.kusithms_hdmedi_project.domain.review.dto.request;

import com.example.kusithms_hdmedi_project.domain.review.dto.response.CreateReviewResponseDto;
import com.example.kusithms_hdmedi_project.domain.review.entity.ExaminationType;
import com.example.kusithms_hdmedi_project.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Getter
public class CreateReviewRequestDto {

    private Long hospitalId;

    private int rating;

    private String content;

    private String doctor;

    private int price;

    private List<ExaminationType> examinations;


    @Builder
    public static CreateReviewRequestDto of(Long hospitalId,
                                            int rating,
                                            String content,
                                            String doctor,
                                            int price,
                                            List<ExaminationType> examinations) {
        return CreateReviewRequestDto.builder()
                .hospitalId(hospitalId)
                .rating(rating)
                .content(content)
                .doctor(doctor)
                .price(price)
                .examinations(examinations)
                .build();
    }

}
