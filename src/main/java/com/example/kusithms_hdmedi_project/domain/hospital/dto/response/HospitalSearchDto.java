package com.example.kusithms_hdmedi_project.domain.hospital.dto.response;

import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HospitalSearchDto {
    private Long hospitalId;
    private String name;
    private String address;
    private double averageRating;
    private int numberOfReview;

    public static HospitalSearchDto of(Hospital hospital) {
        return HospitalSearchDto.builder()
                .hospitalId(hospital.getId())
                .name(hospital.getName())
                .address(hospital.getAddress())
                .averageRating(hospital.getAverageRating())
                .numberOfReview(hospital.getNumberOfReviews())
                .build();
    }
}
