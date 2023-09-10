package com.example.kusithms_hdmedi_project.domain.review.entity;


import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import com.example.kusithms_hdmedi_project.global.common.BaseTimeEntity;
import lombok.AccessLevel;
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
    private Integer rating;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "doctor_name")
    private String doctor;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewExamination> reviewExaminations= new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    //Many to One <-> hospital

}
