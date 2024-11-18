package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	public Page<Restaurant> findByNameLikeOrderByCreatedAtDesc(String keyword, Pageable pageable);
	public Page<Restaurant> findAllByOrderByCreatedAtDesc(Pageable pageable);
    //カテゴリの検索
	public Page<Restaurant> findByCategoryOrderByCreatedAtDesc(Category category, Pageable pageable);
	public List<Restaurant> findTop10ByOrderByCreatedAtDesc();

}
