package com.example.kusithms_hdmedi_project.domain.review.entity;

public enum ExaminationType {
    CAT, FULL_BATTERY, EEG, COUNSEL, MEDICINE, ETC;

    public static ExaminationType of(ReviewExamination reviewExamination){
        return reviewExamination.getReviewType();
    }
}
