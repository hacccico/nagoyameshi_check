package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.entity.User;

import jakarta.transaction.Transactional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{
	public List<Favorite> findByRestaurant(Restaurant restaurant);
	public Page<Favorite> findByUser(User user, Pageable pageable);
	
	@Transactional
	public Favorite findByRestaurantAndUser(Restaurant restaurant, User user);

}
