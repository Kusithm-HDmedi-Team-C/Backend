package com.example.kusithms_hdmedi_project.domain.hospital.dto.response;

import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HospitalDto {
    private Long hospitalId;
    private String name;
    private double averageRating;
    private int numberOfReviews;
    private String address;

    public static HospitalDto of(Hospital hospital) {
        return HospitalDto.builder()
                .hospitalId(hospital.getId())
                .name(hospital.getName())
                .averageRating(hospital.getAverageRating())
                .numberOfReviews(hospital.getNumberOfReviews())
                .address(hospital.getAddress())
                .build();
    }
}
