package com.example.kusithms_hdmedi_project.domain.hospital.service;

import com.example.kusithms_hdmedi_project.domain.hospital.dto.SearchType;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalPageDto;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalSearchDto;
import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import com.example.kusithms_hdmedi_project.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalPageDto getHospitalPage(SearchType searchType, int pageNumber, int pageSize) {
        Sort sort = Sort.by(searchType.getHospitalTableValue()).descending()
                .and(Sort.by("name").ascending());
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Hospital> page = hospitalRepository.findAll(pageRequest);
        return HospitalPageDto.of(page);
    }

    public List<HospitalSearchDto> searchHospitalsByName(String hospitalName) {
        PageRequest pageRequest = PageRequest.of(0, 5);
        return hospitalRepository.findByNameContaining(hospitalName, pageRequest).getContent().stream()
                .map(HospitalSearchDto::of)
                .collect(Collectors.toList());

    }
}
