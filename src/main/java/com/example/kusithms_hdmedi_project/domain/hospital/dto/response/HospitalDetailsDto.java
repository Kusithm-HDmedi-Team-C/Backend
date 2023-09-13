package com.example.kusithms_hdmedi_project.domain.hospital.dto.response;

import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import com.example.kusithms_hdmedi_project.domain.review.dto.response.GetHospitalVReviewResponseDto;
import com.example.kusithms_hdmedi_project.domain.review.entity.VerifiedReview;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class HospitalDetailsDto {
    private Long hospitalId;
    private String name;
    private double averageRating;
    private String address;
    private String url;
    private String mapUrl;
    private List<GetHospitalVReviewResponseDto> reviews;
    private int pageNumber;
    private int count;
    private boolean hasNextPage;

    public static HospitalDetailsDto of(Hospital hospital, Page<VerifiedReview> page) {
        return HospitalDetailsDto.builder()
                .hospitalId(hospital.getId())
                .name(hospital.getName())
                .averageRating(hospital.getAverageRating())
                .address(hospital.getAddress())
                .url(hospital.getUrl())
                .mapUrl(hospital.getMapUrl())
                .reviews(page.getContent().stream()
                        .map(GetHospitalVReviewResponseDto::of)
                        .collect(Collectors.toList()))
                .pageNumber(page.getNumber())
                .count(page.getNumberOfElements())
                .hasNextPage(page.hasNext())
                .build();
    }
}
