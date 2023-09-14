package com.example.kusithms_hdmedi_project.domain.survey.dto.Request;

import lombok.Getter;

import java.util.List;

@Getter
public class TotalSurveyResultRequestDto {
    private List<SurveyResultRequestDto> surveyResults;
}
