package com.example.kusithms_hdmedi_project.domain.hospital.entity;

import com.example.kusithms_hdmedi_project.domain.review.entity.VerifiedReview;
import com.example.kusithms_hdmedi_project.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(indexes = {
                @Index(name = "idx_hospital_number_of_reviews_name", columnList = "numberOfReviews, name"),
                @Index(name = "idx_hospital_average_rating_name", columnList = "averageRating, name")})
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
    private List<VerifiedReview> verifiedReviews = new ArrayList<>();

    @Builder
    public Hospital(String name, String telephone, String url, String mapUrl, int numberOfReviews, int totalRating, double averageRating, String area, String area1, String area2, String area3) {
        this.name = name;
        this.telephone = telephone;
        this.url = url;
        this.mapUrl = mapUrl;
        this.numberOfReviews = numberOfReviews;
        this.totalRating = totalRating;
        this.averageRating = averageRating;
        this.area = area;
        this.area1 = area1;
        this.area2 = area2;
        this.area3 = area3;
    }


    public String getAddress() {
        return Stream.of(area1, area2, area3, area)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.joining(" "));
    }

    public void addVerifiedReview(VerifiedReview verifiedReview) {
        this.verifiedReviews.add(verifiedReview);
        this.totalRating += verifiedReview.getRating();
        this.numberOfReviews += 1;
        double avgRating = (double) this.totalRating / this.numberOfReviews;
        this.averageRating = (Math.floor(avgRating * 10) / 10);
    }
}
