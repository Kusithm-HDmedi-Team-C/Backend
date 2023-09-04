package com.example.kusithms_hdmedi_project.domain.sample.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SampleResponseDto {
    private Long id;
    private String message;

    public static SampleResponseDto of(Long id, String message) {
        return SampleResponseDto.builder()
                .id(id)
                .message(message)
                .build();
    }
}
