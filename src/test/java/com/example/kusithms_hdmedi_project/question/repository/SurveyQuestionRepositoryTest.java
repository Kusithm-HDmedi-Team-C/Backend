package com.example.kusithms_hdmedi_project.question.repository;

import com.example.kusithms_hdmedi_project.question.entity.QuestionType;
import com.example.kusithms_hdmedi_project.question.entity.SurveyQuestion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SurveyQuestionRepositoryTest {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SurveyQuestionRepository surveyQuestionRepository;

    @Test
    public void 전체_설문문항_조회_테스트() throws Exception {
        // given
        SurveyQuestion surveyQuestion1 = SurveyQuestion.builder()
                .content("1번 문항")
                .type(QuestionType.carelessness)
                .build();
        SurveyQuestion surveyQuestion2 = SurveyQuestion.builder()
                .content("2번 문항")
                .type(QuestionType.impulsivity)
                .build();
        SurveyQuestion surveyQuestion3 = SurveyQuestion.builder()
                .content("3번 문항")
                .build();

        em.persist(surveyQuestion1);
        em.persist(surveyQuestion2);
        em.persist(surveyQuestion3);

        em.flush();
        em.clear();

        // when
        List<SurveyQuestion> questions = surveyQuestionRepository.findAll();

        // then
        Assertions.assertThat(questions.size()).isEqualTo(3);

        Assertions.assertThat(questions.get(0).getId()).isEqualTo(surveyQuestion1.getId());
        Assertions.assertThat(questions.get(1).getId()).isEqualTo(surveyQuestion2.getId());
        Assertions.assertThat(questions.get(2).getId()).isEqualTo(surveyQuestion3.getId());
    }
}