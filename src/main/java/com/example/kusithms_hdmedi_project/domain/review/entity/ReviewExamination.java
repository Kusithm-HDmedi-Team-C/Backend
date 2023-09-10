package com.example.kusithms_hdmedi_project.domain.review.entity;

import com.example.kusithms_hdmedi_project.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ReviewExamination extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_examination_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verified_review_id")
    private VerifiedReview verifiedReview;


    @Column(name = "review_type")
    @Enumerated(EnumType.STRING)
    private ExaminationType reviewType;

}
