package com.example.kusithms_hdmedi_project.domain.survey.repository;

import com.example.kusithms_hdmedi_project.domain.survey.entity.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {
    List<SurveyQuestion> findAll();
}
