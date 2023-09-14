package com.example.kusithms_hdmedi_project.domain.survey.entity;

import com.example.kusithms_hdmedi_project.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class SurveyQuestion extends BaseTimeEntity {
    @Id
    @Column(name = "surveyquestion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToMany(mappedBy = "question")
    private List<Example> examples = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private QuestionType type;

    @Builder
    public SurveyQuestion(String content, QuestionType type) {
        this.content = content;
        this.type = type;
    }
}
