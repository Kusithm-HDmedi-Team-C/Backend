package com.example.kusithms_hdmedi_project.domain.hospital.dto.response;

import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class HospitalPageDto {
    private List<HospitalDto> hospitals;
    private int pageNumber;
    private int count;
    private boolean hasNextPage;

    public static HospitalPageDto of(Page<Hospital> page) {
        return HospitalPageDto.builder()
                .hospitals(page.getContent().stream()
                        .map(HospitalDto::of)
                        .collect(Collectors.toList()))
                .pageNumber(page.getNumber())
                .count(page.getNumberOfElements())
                .hasNextPage(page.hasNext())
                .build();
    }
}
