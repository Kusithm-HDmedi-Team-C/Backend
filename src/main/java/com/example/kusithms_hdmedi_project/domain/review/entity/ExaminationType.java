package com.example.kusithms_hdmedi_project.domain.review.entity;

public enum ExaminationType {
    CONSULTATION, CAT, AAA, BBB, CCC, ETC;

    public static ExaminationType of(ReviewExamination reviewExamination){
        return reviewExamination.getReviewType();
    }
}
