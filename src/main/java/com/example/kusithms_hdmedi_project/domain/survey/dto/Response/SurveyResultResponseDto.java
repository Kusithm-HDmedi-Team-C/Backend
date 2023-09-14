package com.example.kusithms_hdmedi_project.domain.survey.dto.Response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SurveyResultResponseDto {
    private int totalScore;

    private int carelessnessScore;

    private int impulsivityScore;

    public static SurveyResultResponseDto of(int totalScore, int carelessnessScore, int impulsivityScore) {
        return SurveyResultResponseDto.builder()
                .totalScore(totalScore)
                .carelessnessScore(carelessnessScore)
                .impulsivityScore(impulsivityScore)
                .build();
    }
}
