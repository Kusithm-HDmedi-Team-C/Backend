package com.example.kusithms_hdmedi_project.domain.hospital.repository;

import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Optional<Hospital> findById(Long id);
}