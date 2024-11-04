package com.example.nagoyameshi.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.repository.ReservationRepository;
import com.example.nagoyameshi.repository.RestaurantRepository;
import com.example.nagoyameshi.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final RestaurantRepository restaurantRepository;
	private final UserRepository userRepository;
	
	public ReservationService(ReservationRepository reservationRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.restaurantRepository = restaurantRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void create(Map<String, String> paymentIntentObject) {
		Reservation reservation = new Reservation();
		
		Integer restaurantId = Integer.valueOf(paymentIntentObject.get("restaurantId"));
		Integer userId = Integer.valueOf(paymentIntentObject.get("userId"));
		
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
		User user = userRepository.getReferenceById(userId);
		//reservedDateTimeの定義
		
		Integer numberOfPeople = Integer.valueOf(paymentIntentObject.get("numberOfPeople"));
		
		reservation.setRestaurant(restaurant);
		reservation.setUser(user);
		//reservedDateTimeをセット
		
		reservation.setNumberOfPeople(numberOfPeople);
		
		reservationRepository.save(reservation);
	}
	//宿泊人数が定員以下かどうかをチェックする
	public boolean isWithinCapacity(Integer numberOfPeople, Integer capacity) {
		return numberOfPeople <= capacity;
	}
	
	//予約日時が未来かチェックする

}
