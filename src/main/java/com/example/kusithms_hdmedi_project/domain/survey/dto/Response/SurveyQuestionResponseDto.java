package com.example.kusithms_hdmedi_project.domain.survey.dto.Response;

import com.example.kusithms_hdmedi_project.domain.survey.entity.QuestionType;
import com.example.kusithms_hdmedi_project.domain.survey.entity.SurveyQuestion;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class SurveyQuestionResponseDto {
    private Long questionId;

    private String content;

    private List<ExampleResponseDto> examples;

    private QuestionType type;

    public static SurveyQuestionResponseDto of(SurveyQuestion question) {
        return SurveyQuestionResponseDto.builder()
                .questionId(question.getId())
                .content(question.getContent())
                .examples(
                        question.getExamples().stream()
                                .map(ExampleResponseDto::new)
                                .collect(Collectors.toList()))
                .type(question.getType())
                .build();
    }
}
