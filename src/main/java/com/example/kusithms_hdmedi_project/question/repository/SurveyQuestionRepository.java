package com.example.kusithms_hdmedi_project.question.repository;

import com.example.kusithms_hdmedi_project.question.entity.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {
    List<SurveyQuestion> findAll();
}
