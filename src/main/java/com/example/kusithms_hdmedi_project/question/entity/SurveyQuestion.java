package com.example.kusithms_hdmedi_project.question.entity;

import com.example.kusithms_hdmedi_project.global.common.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SurveyQuestion extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "surveyquestion_id")
    private Long id;

    private String content;

    @OneToMany(mappedBy = "question")
    private List<Example> examples = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private QuestionType type;
}
