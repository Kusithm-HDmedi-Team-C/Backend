package com.example.kusithms_hdmedi_project.question.controller;

import com.example.kusithms_hdmedi_project.global.common.BaseResponse;
import com.example.kusithms_hdmedi_project.global.common.SuccessCode;
import com.example.kusithms_hdmedi_project.question.dto.Request.TotalSurveyResultRequestDto;
import com.example.kusithms_hdmedi_project.question.dto.Response.SurveyResultResponseDto;
import com.example.kusithms_hdmedi_project.question.service.SurveyResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/survey")
@RequiredArgsConstructor
@Controller
public class SurveyResultController {

    private final SurveyResultService surveyResultService;
    
    @PostMapping("/result")
    public ResponseEntity<BaseResponse<?>> calculateSurveyResult(@RequestBody TotalSurveyResultRequestDto totalSurveyResultRequestDto) {
        SurveyResultResponseDto calculatedSurveyResult = surveyResultService.calculateSurveyResult(totalSurveyResultRequestDto.getSurveyResults());
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, calculatedSurveyResult));
    }
}
