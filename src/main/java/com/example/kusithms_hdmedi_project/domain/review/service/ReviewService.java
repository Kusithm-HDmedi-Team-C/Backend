package com.example.kusithms_hdmedi_project.domain.review.service;

import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import com.example.kusithms_hdmedi_project.domain.hospital.repository.HospitalRepository;
import com.example.kusithms_hdmedi_project.domain.review.dto.request.CreateReviewRequestDto;
import com.example.kusithms_hdmedi_project.domain.review.dto.response.CreateReviewResponseDto;
import com.example.kusithms_hdmedi_project.domain.review.dto.response.GetHospitalVReviewResponseDto;
import com.example.kusithms_hdmedi_project.domain.review.dto.response.VerifyReviewResponseDto;
import com.example.kusithms_hdmedi_project.domain.review.entity.Review;
import com.example.kusithms_hdmedi_project.domain.review.entity.ReviewExamination;
import com.example.kusithms_hdmedi_project.domain.review.entity.VerifiedReview;
import com.example.kusithms_hdmedi_project.domain.review.repository.ReviewExaminationRepository;
import com.example.kusithms_hdmedi_project.domain.review.repository.VerifiedReviewRepository;
import com.example.kusithms_hdmedi_project.domain.review.repository.ReviewRepository;
import com.example.kusithms_hdmedi_project.global.error.exception.EntityNotFoundException;
import com.example.kusithms_hdmedi_project.global.infra.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.kusithms_hdmedi_project.domain.review.entity.ReviewExamination.createExaminationWithReview;
import static com.example.kusithms_hdmedi_project.domain.review.entity.VerifiedReview.createVerifiedReviewWithHospital;
import static com.example.kusithms_hdmedi_project.global.error.exception.ErrorCode.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final VerifiedReviewRepository verifiedReviewRepository;
    private final ReviewExaminationRepository reviewExaminationRepository;
    private final HospitalRepository hospitalRepository;
    private final S3Service s3Service;


    public List<GetHospitalVReviewResponseDto> getHospitalVerifiedReview(Long hospitalId){
        Hospital findHospital = getHospital(hospitalId);
        return findHospital.getVerifiedReviews().stream()
                .map(GetHospitalVReviewResponseDto::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    public CreateReviewResponseDto createReview(CreateReviewRequestDto createReviewRequestDto,
                                                MultipartFile file) throws IOException {
        String imgUrl = s3Service.upload(file);

        Hospital findHospital = getHospital(createReviewRequestDto.getHospitalId());
        Review review = createNewReview(findHospital, createReviewRequestDto, imgUrl);
        List<ReviewExamination> reviewExaminations = createNewExaminationWithReview(review, createReviewRequestDto);

        reviewExaminationRepository.saveAll(reviewExaminations);
        reviewRepository.save(review);
        return CreateReviewResponseDto.of(review.getId());
    }

    @Transactional(readOnly = false)
    public VerifyReviewResponseDto verifyReview(Long reviewId) {
        Review findReview = findReview(reviewId);
        VerifiedReview verifiedReview = createVerifiedReviewWithHospital(findReview);

        List<ReviewExamination> reviewExaminations = verifiedReview.getReviewExaminations();
        reviewExaminations.forEach(data -> data.verifyReviewInExamination(verifiedReview));

        reviewRepository.delete(findReview);
        verifiedReviewRepository.save(verifiedReview);
        return VerifyReviewResponseDto.of(verifiedReview.getId());
    }


    private Hospital getHospital(Long hospitalId) {
        Hospital findHospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
        return findHospital;
    }

    private Review createNewReview(Hospital hospital, CreateReviewRequestDto createReviewRequestDto, String imgUrl) {
        return Review.builder()
                .hospital(hospital)
                .content(createReviewRequestDto.getContent())
                .rating(createReviewRequestDto.getRating())
                .imageUrl(imgUrl)
                .price(createReviewRequestDto.getPrice())
                .doctor(createReviewRequestDto.getDoctor())
                .build();
    }

    private List<ReviewExamination> createNewExaminationWithReview(Review review, CreateReviewRequestDto createReviewRequestDto) {
        List<ReviewExamination> reviewExaminations = createReviewRequestDto.getExaminations().stream()
                .map(inData -> createExaminationWithReview(review, inData))
                .collect(Collectors.toList());
        return reviewExaminations;
    }

    private Review findReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
        return review;
    }


}
