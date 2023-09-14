package com.example.kusithms_hdmedi_project.domain.survey.entity;

import com.example.kusithms_hdmedi_project.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Example extends BaseTimeEntity {

    @Id
    @Column(name = "example_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surveyquestion_id")
    private SurveyQuestion question;

    @Builder
    public Example(String content, SurveyQuestion question) {
        this.content = content;
        this.question = question;
    }
}
