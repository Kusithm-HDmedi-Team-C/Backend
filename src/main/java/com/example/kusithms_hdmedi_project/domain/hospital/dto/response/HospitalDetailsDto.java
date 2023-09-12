package com.example.kusithms_hdmedi_project.domain.hospital.dto.response;

import com.example.kusithms_hdmedi_project.domain.review.entity.VerifiedReview;
import lombok.Data;

import java.util.List;

@Data
public class HospitalDetailsDto {
    private String name;
    private double averageRating;
    private String address;
    private String url;
    private String mapUrl;
    // TODO VerifiedReview -> VerifiedReviewDto 변경 필요
    private List<VerifiedReview> reviews;
}
