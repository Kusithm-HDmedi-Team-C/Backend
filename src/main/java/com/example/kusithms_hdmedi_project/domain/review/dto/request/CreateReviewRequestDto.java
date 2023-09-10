package com.example.kusithms_hdmedi_project.domain.review.dto.request;

import com.example.kusithms_hdmedi_project.domain.review.entity.ExaminationType;
import com.example.kusithms_hdmedi_project.domain.review.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateReviewRequestDto {

    private Long hospitalId;

    private Integer rating;

    private String content;

    private String doctor;

    private Integer price;

    private List<ExaminationType> examinations;

}
