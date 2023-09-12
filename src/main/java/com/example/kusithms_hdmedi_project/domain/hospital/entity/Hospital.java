package com.example.kusithms_hdmedi_project.domain.hospital.entity;

import com.example.kusithms_hdmedi_project.domain.review.entity.Review;
import com.example.kusithms_hdmedi_project.domain.review.entity.VerifiedReview;
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
public class Hospital extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    private String name;

    private String telephone;

    private String url;

    private String mapUrl;

    private int numberOfReviews;

    private int totalRating;

    private double averageRating;

    private String area;

    private String area1;

    private String area2;

    private String area3;

    @OneToMany(mappedBy = "hospital")
    private final List<VerifiedReview> verifiedReviews = new ArrayList<>();

    @Builder
    public Hospital(String name, String telephone, String url, String mapUrl, int numberOfReviews, int totalRating, double averageRating, String area, String area1, String area2, String area3) {
        this.name = name;
        this.telephone = telephone;
        this.url = url;
        this.mapUrl = mapUrl;
        this.numberOfReviews = numberOfReviews;
        // TODO 평균으로 변경 고려
        this.totalRating = totalRating;
        this.averageRating = averageRating;
        this.area = area;
        this.area1 = area1;
        this.area2 = area2;
        this.area3 = area3;
    }

    public double getAverageRating() {
        return (double) getTotalRating() / (getNumberOfReviews() == 0 ? 1: getNumberOfReviews());
    }

    public String getAddress() {
        return area1 + " " + area2 + " " + area3 + " " + area;
    }
}
