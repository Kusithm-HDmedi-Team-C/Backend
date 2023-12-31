package com.example.kusithms_hdmedi_project.domain.survey.service;

import com.example.kusithms_hdmedi_project.domain.survey.dto.Response.SurveyQuestionResponseDto;
import com.example.kusithms_hdmedi_project.domain.survey.entity.SurveyQuestion;
import com.example.kusithms_hdmedi_project.domain.survey.repository.SurveyQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyQuestionService {

    private final SurveyQuestionRepository surveyQuestionRepository;

    public List<SurveyQuestionResponseDto> findSurveyQuestions() {
        List<SurveyQuestion> questions = surveyQuestionRepository.findAll();
        return questions.stream()
                .map(SurveyQuestionResponseDto::of)
                .collect(Collectors.toList());
    }
}
