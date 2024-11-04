package com.example.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReviewEditForm;
import com.example.nagoyameshi.form.ReviewPostForm;
import com.example.nagoyameshi.repository.RestaurantRepository;
import com.example.nagoyameshi.repository.ReviewRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.ReviewService;

@Controller
@RequestMapping("/restaurants/{restaurantId}/review")
public class ReviewController {
	private final ReviewRepository reviewRepository;
	private final ReviewService reviewService;
	private final RestaurantRepository restaurantRepository;
	
	public ReviewController(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository, ReviewService reviewService) {
		this.reviewRepository = reviewRepository;
		this.reviewService = reviewService;
		this.restaurantRepository = restaurantRepository;
	}
	
	//家の詳細ページ
	@GetMapping
	public String index(Model model, @PageableDefault(page = 0, size = 6, sort = "id", direction = Direction.ASC)Pageable pageable) {
		Page<Review> reviewPage;
		reviewPage = reviewRepository.findAll(pageable);
		model.addAttribute("reviewPage", reviewPage);
		return "restaurants/show";
	}
	
	//レビュー一覧の表示
	@GetMapping("/table")
	public String table(@PathVariable(name = "restaurantId") Integer restaurantId, Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC)Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		Page<Review> reviewPage;
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
		reviewPage = reviewRepository.findByRestaurantOrderByCreatedAtDesc(restaurant, pageable);
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("reviewPage", reviewPage);
		model.addAttribute("loginUserId", userDetailsImpl != null ? userDetailsImpl.getUser().getId() : null);
		return "review/table";
	}
	
	//レビューの投稿
	@GetMapping("/post")
	public String post(@PathVariable(name = "restaurantId") Integer restaurantId, Model model) {
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
		
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("reviewPostForm", new ReviewPostForm());
		return "review/post";
	}
	
	//レビューの作成
		@PostMapping("/create")
		public String create(@PathVariable(name = "restaurantId") Integer restaurantId, @ModelAttribute @Validated ReviewPostForm reviewPostForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
			if(bindingResult.hasErrors()) {
				return "restaurants/shouw/review/post";
			}
			User user = userDetailsImpl.getUser();
			reviewPostForm.setUserId(user.getId());
			reviewPostForm.setRestaurantId(restaurantId);
			reviewService.create(reviewPostForm);
			
			redirectAttributes.addFlashAttribute("successMessage", "レビューを登録しました。");
			return "redirect:/restaurants/{restaurantId}";
		}
		
		//レビューの編集
		@GetMapping("/{id}/edit")
		public String edit(@PathVariable(name = "id") Integer id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name ="restaurantId") Integer restaurantId, Model model) {
			Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
			Review review = reviewRepository.getReferenceById(id);
			ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), restaurant.getId(), review.getReviewStar(), review.getReviewComment());
			
			model.addAttribute("restaurant", restaurant);
			model.addAttribute("reviewEditForm", reviewEditForm);
			return "review/edit";
		}
		
		//レビューの更新
		@PostMapping("{id}/update")
		public String update(@PathVariable(name = "id") Integer restaurantId,@ModelAttribute @Validated ReviewEditForm reviewEditForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
			if(bindingResult.hasErrors()) {
				return "review/edit";
			}
			Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
			model.addAttribute("restaurant", restaurant);
			
			reviewService.update(reviewEditForm);
			redirectAttributes.addFlashAttribute("successMessage", "レビューを編集しました。");
			return "redirect:/restaurants/{restaurantId}";
		}
		
		//レビューの削除
		@GetMapping("{id}/delete")
		public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
			reviewRepository.deleteById(id);
			redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");
			return "redirect:/restaurants/{restaurantId}";
		}

}
