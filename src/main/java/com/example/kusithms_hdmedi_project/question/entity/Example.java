package com.example.kusithms_hdmedi_project.question.entity;

import com.example.kusithms_hdmedi_project.global.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class Example extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "example_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surveyquestion_id")
    private SurveyQuestion question;
}
