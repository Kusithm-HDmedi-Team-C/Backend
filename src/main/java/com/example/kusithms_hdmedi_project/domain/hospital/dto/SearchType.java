package com.example.kusithms_hdmedi_project.domain.hospital.dto;

import lombok.Getter;

@Getter
public enum SearchType {
    AVERAGE_RATING("averageRating"), NUMBER_OF_REVIEWS("numberOfReviews");

    private final String hospitalTableValue;

    SearchType(String hospitalTableValue) {
        this.hospitalTableValue = hospitalTableValue;
    }
}
