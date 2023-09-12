package com.example.kusithms_hdmedi_project.domain.hospital.dto.response;

import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HospitalSearchDto {
    private String name;
    private String address;

    public static HospitalSearchDto of(Hospital hospital) {
        return HospitalSearchDto.builder()
                .name(hospital.getName())
                .address(hospital.getAddress())
                .build();
    }
}
