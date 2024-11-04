package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReviewEditForm;
import com.example.nagoyameshi.form.ReviewPostForm;
import com.example.nagoyameshi.repository.RestaurantRepository;
import com.example.nagoyameshi.repository.ReviewRepository;
import com.example.nagoyameshi.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final RestaurantRepository restaurantRepository;
	private final UserRepository userRepository;
	
	public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
		this.reviewRepository = reviewRepository;
		this.restaurantRepository = restaurantRepository;
		this.userRepository = userRepository;
	}
	
	//新規レビューをデータベースに保存
	@Transactional
	public void create(ReviewPostForm reviewPostForm) {
		Review review = new Review();
		Restaurant restaurant = restaurantRepository.getReferenceById(reviewPostForm.getRestaurantId());
		User user = userRepository.getReferenceById(reviewPostForm.getUserId());
		review.setRestaurant(restaurant);
		review.setUser(user);
		review.setReviewStar(reviewPostForm.getReviewStar());
		review.setReviewComment(reviewPostForm.getReviewComment());
		reviewRepository.save(review);
	}
	
	//レビューの変更を保存
	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
		review.setReviewStar(reviewEditForm.getReviewStar());
		review.setReviewComment(reviewEditForm.getReviewComment());
		reviewRepository.save(review);
	}

}
