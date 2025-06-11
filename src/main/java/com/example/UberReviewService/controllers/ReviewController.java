package com.example.UberReviewService.controllers;

import com.example.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import com.example.UberReviewService.dtos.CreateReviewDto;
import com.example.UberReviewService.dtos.ReviewDto;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.services.ReviewService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;

    private CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;

    public ReviewController(ReviewService reviewService, CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter) {
        this.reviewService = reviewService;
        this.createReviewDtoToReviewAdapter = createReviewDtoToReviewAdapter;
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@RequestBody CreateReviewDto request){
        Review incomingReview = this.createReviewDtoToReviewAdapter.convertDto(request);
        if(incomingReview == null){
            return new ResponseEntity<>("Invalid Arguments", HttpStatus.BAD_REQUEST);
        }
        Review review = this.reviewService.publishReview(incomingReview);
        ReviewDto response = ReviewDto.builder()
                .id(review.getId())
                .booking(review.getBooking().getId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        List<Review> allReviews = reviewService.findAllReviews();
        return new ResponseEntity<>(allReviews, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long reviewId){
        try{
            Optional<Review> reviewById = reviewService.findReviewById(reviewId);
            return new ResponseEntity<>(reviewId, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteById(@PathVariable Long reviewId){
        try{
            boolean isDeleted = reviewService.deleteReveiewById(reviewId);
            if(!isDeleted) return new ResponseEntity<>("unable to delete the review", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review newReviewData){
        try{
            Review updatedReview = reviewService.updateReview(reviewId, newReviewData);
            if(updatedReview != null) return new ResponseEntity<>(updatedReview, HttpStatus.OK);
            return new ResponseEntity<>("Review cannot be updated", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
