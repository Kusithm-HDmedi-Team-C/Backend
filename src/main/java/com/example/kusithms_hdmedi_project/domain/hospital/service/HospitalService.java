package com.example.kusithms_hdmedi_project.domain.hospital.service;

import com.example.kusithms_hdmedi_project.domain.hospital.dto.SearchType;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalDetailsDto;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalPageDto;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalSearchDto;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalSearchPageDto;
import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import com.example.kusithms_hdmedi_project.domain.hospital.repository.HospitalRepository;
import com.example.kusithms_hdmedi_project.domain.review.entity.VerifiedReview;
import com.example.kusithms_hdmedi_project.domain.review.repository.VerifiedReviewRepository;
import com.example.kusithms_hdmedi_project.global.error.exception.EntityNotFoundException;
import com.example.kusithms_hdmedi_project.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final VerifiedReviewRepository verifiedReviewRepository;

    public HospitalPageDto getHospitalPage(SearchType searchType, int pageNumber, int pageSize) {
        Sort sort = Sort.by(searchType.getHospitalTableValue()).descending();
        if (SearchType.AVERAGE_RATING.equals(searchType)) {
            sort = sort.and(Sort.by(SearchType.NUMBER_OF_REVIEWS.getHospitalTableValue()).descending());
        } else {
            sort = sort.and(Sort.by(SearchType.AVERAGE_RATING.getHospitalTableValue()).descending());
        }
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Hospital> page = hospitalRepository.findAll(pageRequest);
        return HospitalPageDto.of(page);
    }

    public HospitalSearchPageDto searchHospitalsByName(String hospitalName) {
        Sort sort = Sort.by(SearchType.AVERAGE_RATING.getHospitalTableValue()).descending()
                .and(Sort.by(SearchType.NUMBER_OF_REVIEWS.getHospitalTableValue()).descending());
        PageRequest pageRequest = PageRequest.of(0, 5, sort);
        Page<Hospital> hospitals = hospitalRepository.findByNameContaining(hospitalName, pageRequest);
        return HospitalSearchPageDto.of(hospitals);
    }

    public HospitalDetailsDto getHospitalDetails(Long hospitalId, int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
        Page<VerifiedReview> page = verifiedReviewRepository.findByHospitalIdOrderByCreateDateDesc(hospitalId, pageRequest);
        return HospitalDetailsDto.of(hospital, page);
    }

    public void getHospitalDetailsFromReviews(Long hospitalId, int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Map<Hospital, List<VerifiedReview>> collect = verifiedReviewRepository.findByHospitalIdOrderByCreateDateDesc(hospitalId, pageRequest).stream()
                .collect(Collectors.groupingBy(VerifiedReview::getHospital));
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow();
    }
}
