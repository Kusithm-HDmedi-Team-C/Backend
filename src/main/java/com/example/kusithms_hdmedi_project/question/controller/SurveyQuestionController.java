package com.example.kusithms_hdmedi_project.question.controller;

import com.example.kusithms_hdmedi_project.global.common.BaseResponse;
import com.example.kusithms_hdmedi_project.global.common.SuccessCode;
import com.example.kusithms_hdmedi_project.question.dto.Response.SurveyQuestionResponseDto;
import com.example.kusithms_hdmedi_project.question.service.SurveyQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/survey")
@RequiredArgsConstructor
@RestController
public class SurveyQuestionController {

    private final SurveyQuestionService surveyQuestionService;

    @GetMapping("/questions")
    public ResponseEntity<BaseResponse<?>> findSurveyQuestions() {
        List<SurveyQuestionResponseDto> questions = surveyQuestionService.findSurveyQuestions();
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, questions));
    }
}
