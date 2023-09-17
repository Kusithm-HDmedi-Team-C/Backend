package com.example.kusithms_hdmedi_project.domain.review.entity;


import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import com.example.kusithms_hdmedi_project.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "verified_review")
public class VerifiedReview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verified_review_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "doctor_name")
    private String doctor;

    @Column(name = "price")
    private Integer price;

    @BatchSize(size = 20)
    @OneToMany(mappedBy = "verifiedReview", fetch = FetchType.LAZY)
    private List<ReviewExamination> reviewExaminations= new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Builder
    public VerifiedReview(String content, Integer rating, String imageUrl, String doctor, Integer price, Hospital hospital, List<ReviewExamination> reviewExaminations) {
        this.content = content;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.doctor = doctor;
        this.price = price;
        this.hospital = hospital;
        this.reviewExaminations = reviewExaminations;
    }
    public static VerifiedReview createVerifiedReviewWithHospital(Review review) {
        VerifiedReview verifiedReview = VerifiedReview.builder()
                .hospital(review.getHospital())
                .content(review.getContent())
                .rating(review.getRating())
                .imageUrl(review.getImageUrl())
                .price(review.getPrice())
                .doctor(review.getDoctor())
                .reviewExaminations(review.getReviewExaminations())
                .build();
        verifiedReview.getHospital().addVerifiedReview(verifiedReview);
        return verifiedReview;
    }

}
