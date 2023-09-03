package com.example.kusithms_hdmedi_project.domain.sample.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Sample {
    @Id
    @Column(name = "sample_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Builder
    public Sample(String message) {
        this.message = message;
    }
}
