package com.example.kusithms_hdmedi_project.domain.sample.repository;

import com.example.kusithms_hdmedi_project.domain.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleRepository extends JpaRepository<Sample, Long> {
    List<Sample> findAllByMessage(String message);
}
