package com.example.kusithms_hdmedi_project.question.dto.Response;

import com.example.kusithms_hdmedi_project.question.entity.Example;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ExampleResponseDto {
    private Long exampleId;

    private String content;

    public ExampleResponseDto(Example example) {
        this.exampleId = example.getId();
        this.content = example.getContent();
    }

    public static ExampleResponseDto of(Example example) {
        return ExampleResponseDto.builder()
                .exampleId(example.getId())
                .content(example.getContent())
                .build();
    }
}
