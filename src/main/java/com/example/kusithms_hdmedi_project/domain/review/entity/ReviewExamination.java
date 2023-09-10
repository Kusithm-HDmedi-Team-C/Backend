package com.example.kusithms_hdmedi_project.domain.review.entity;

import com.example.kusithms_hdmedi_project.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public ReviewExamination(Review review, VerifiedReview verifiedReview, ExaminationType reviewType) {
        this.review = review;
        this.verifiedReview = verifiedReview;
        this.reviewType = reviewType;
    }

    public static ReviewExamination createExaminationWithReview(Review review, ExaminationType reviewType) {
        ReviewExamination reviewExamination = ReviewExamination.builder()
                .review(review)
                .reviewType(reviewType)
                .build();
        review.getReviewExaminations().add(reviewExamination);
        return reviewExamination;
    }
    public static ReviewExamination createExaminationWithVerifiedReview(VerifiedReview verifiedReview, ExaminationType reviewType) {
        ReviewExamination reviewExamination = ReviewExamination.builder()
                .verifiedReview(verifiedReview)
                .reviewType(reviewType)
                .build();
        verifiedReview.getReviewExaminations().add(reviewExamination);
        return reviewExamination;
    }


    public void verifyReviewInExamination(VerifiedReview verifiedReview){
        this.review = null;
        this.verifiedReview = verifiedReview;
    }
}
