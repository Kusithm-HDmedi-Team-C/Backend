package com.example.kusithms_hdmedi_project.domain.review.entity;


import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
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
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private int rating;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "doctor_name")
    private String doctor;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewExamination> reviewExaminations= new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Builder
    public Review(String content, int rating, String imageUrl, String doctor, int price, List<ReviewExamination> reviewExaminations, Hospital hospital) {
        this.content = content;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.doctor = doctor;
        this.price = price;
        this.reviewExaminations = reviewExaminations;
        this.hospital = hospital;
    }

}
