package com.example.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.form.ReservationInputForm;
import com.example.nagoyameshi.repository.FavoriteRepository;
import com.example.nagoyameshi.repository.RestaurantRepository;
import com.example.nagoyameshi.repository.ReviewRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.CategoryService;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
	private final RestaurantRepository restaurantRepository;
	private final ReviewRepository reviewRepository;
	private final FavoriteRepository favoriteRepository;
	private final CategoryService categoryService;
	
	public RestaurantController(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, FavoriteRepository favoriteRepository, CategoryService categoryService) {
		this.restaurantRepository = restaurantRepository;
		this.reviewRepository = reviewRepository;
		this.favoriteRepository = favoriteRepository;
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "category", required = false) String categoryName,
			@RequestParam(name = "order", required = false) String order,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {
		Page<Restaurant> restaurantPage;
		
		//どの検索フォームが送信されたかによって条件分岐
		if (keyword != null && !keyword.isEmpty()) {
			restaurantPage = restaurantRepository.findByNameLikeOrderByCreatedAtDesc("%" + keyword + "%", pageable);
		} else if (categoryName != null) {
			 Category category = categoryService.getCategoryByName(categoryName);
	            if (category != null) {
	                restaurantPage = restaurantRepository.findByCategoryOrderByCreatedAtDesc(category, pageable);
	            } else {
	                restaurantPage = Page.empty();
	            }
		} else {
			restaurantPage = restaurantRepository.findAllByOrderByCreatedAtDesc(pageable);
		}
		model.addAttribute("restaurantPage", restaurantPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("category", categoryName);
		model.addAttribute("order", order);
		
		return "restaurants/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model, @PageableDefault(page = 0, size = 6, sort = "id", direction = Direction.ASC) Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		Restaurant restaurant = restaurantRepository.getReferenceById(id);
		Page<Review> reviewPage = reviewRepository.findByRestaurantOrderByCreatedAtDesc(restaurant, pageable);
		
		//レビューを取得したかどうかのカウント取得
		int count = reviewRepository.countByRestaurant(restaurant);
		
		model.addAttribute("reviewPage", reviewPage);
	    model.addAttribute("restaurant", restaurant);
	    model.addAttribute("reservationInputForm", new ReservationInputForm());

	    // レビューを取得したかどうかのフラグ設定
	    model.addAttribute("reviewFlag", count == 0);

	    Integer loginUserId = null;
	    if (userDetailsImpl != null) {
	        loginUserId = userDetailsImpl.getUser().getId();

	        // ログインユーザーがレビューしているかどうか
	        Review doneReview = reviewRepository.findByRestaurantAndUser(restaurant, userDetailsImpl.getUser());
	        model.addAttribute("doneReviewFlag", doneReview != null);

	        // ログインユーザーがお気に入りに追加しているかどうか
	        Favorite favorite = favoriteRepository.findByRestaurantAndUser(restaurant, userDetailsImpl.getUser());
	        if (favorite != null) {
	            model.addAttribute("favoriteFlag", true);
	            model.addAttribute("favoriteId", favorite.getId());
	        } else {
	            model.addAttribute("favoriteFlag", false);
	        }
	    } else {
	        // ログインしていないときのデフォルト値を設定
	        model.addAttribute("doneReviewFlag", false);
	        model.addAttribute("favoriteFlag", false);
	    }

	    model.addAttribute("loginUserId", loginUserId);

	    return "restaurants/show";
	}
	
	
}
