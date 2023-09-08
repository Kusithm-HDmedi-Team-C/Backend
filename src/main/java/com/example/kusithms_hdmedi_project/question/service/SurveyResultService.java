package com.example.kusithms_hdmedi_project.question.service;

import com.example.kusithms_hdmedi_project.question.dto.Request.SurveyResultRequestDto;
import com.example.kusithms_hdmedi_project.question.dto.Response.SurveyResultResponseDto;
import com.example.kusithms_hdmedi_project.question.entity.SurveyQuestion;
import com.example.kusithms_hdmedi_project.question.repository.SurveyQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.kusithms_hdmedi_project.question.entity.QuestionType.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SurveyResultService {

    private final SurveyQuestionRepository surveyQuestionRepository;

    public SurveyResultResponseDto calculateSurveyResult(List<SurveyResultRequestDto> surveyResults){
        SurveyResultResponseDto calculatedResult = calculateScores(surveyResults);
        return calculatedResult;
    }

    private SurveyResultResponseDto calculateScores(List<SurveyResultRequestDto> surveyResults) {
        int carelessnessScore = 0;
        int impulsivityScore = 0;

        List<SurveyQuestion> questions = surveyQuestionRepository.findAll();

        for(SurveyResultRequestDto surveyResult: surveyResults) {

            for(SurveyQuestion question: questions){

                if(question.getId().equals(surveyResult.getSurveyId())){

                    if(question.getType() == carelessness) {
                        carelessnessScore += surveyResult.getScore();
                    }
                    else {
                        impulsivityScore += surveyResult.getScore();
                    }

                    break;
                }
            }
        }
        return SurveyResultResponseDto.of(carelessnessScore + impulsivityScore, carelessnessScore, impulsivityScore);
    }
}
