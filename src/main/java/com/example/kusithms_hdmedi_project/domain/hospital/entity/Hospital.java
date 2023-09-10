package com.example.kusithms_hdmedi_project.domain.hospital.entity;

import com.example.kusithms_hdmedi_project.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Hospital extends BaseTimeEntity {

    @Id
    @Column(name = "hospital_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String telephone;

    private String url;

    private String mapUrl;

    private int numberOfReviews;

    private int totalRating;

    private String area;

    private String area1;

    private String area2;

    private String area3;

    @Builder
    public Hospital(String name, String telephone, String url, String mapUrl, int numberOfReviews, int totalRating, String area, String area1, String area2, String area3) {
        this.name = name;
        this.telephone = telephone;
        this.url = url;
        this.mapUrl = mapUrl;
        this.numberOfReviews = numberOfReviews;
        this.totalRating = totalRating;
        this.area = area;
        this.area1 = area1;
        this.area2 = area2;
        this.area3 = area3;
    }
}
