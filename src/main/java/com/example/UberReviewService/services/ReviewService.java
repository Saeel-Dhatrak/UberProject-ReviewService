package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    public Optional<Review> findReviewById(Long id);
    public List<Review> findAllReviews();
    public boolean deleteReveiewById(Long id);
    public Review publishReview(Review review);
    public Review updateReview(Long reviewId, Review review);
}
