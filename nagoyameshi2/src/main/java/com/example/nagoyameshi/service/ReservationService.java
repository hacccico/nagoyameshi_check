package com.example.nagoyameshi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReservationRegisterForm;
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
	public void create(ReservationRegisterForm reservationRegisterForm) {
		Reservation reservation = new Reservation();
		Restaurant restaurant = restaurantRepository.getReferenceById(reservationRegisterForm.getRestaurantId());
		User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
		LocalDate reservationDate = LocalDate.parse(reservationRegisterForm.getReservationDate());
		LocalTime reservationTime = LocalTime.parse(reservationRegisterForm.getReservationTime());

		reservation.setRestaurant(restaurant);
		reservation.setUser(user);
		reservation.setReservationDate(reservationDate);
		reservation.setReservationTime(reservationTime);
		reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());

		reservationRepository.save(reservation);
	}
	//宿泊人数が定員以下かどうかをチェックする
	public boolean isWithinCapacity(Integer numberOfPeople, Integer capacity) {
		return numberOfPeople <= capacity;
	}
	
	//予約日時が未来かチェックする
	public boolean isAfterCurrentTime(LocalDate reservationDate, LocalTime reservationTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reservationDateTime = LocalDateTime.of(reservationDate, reservationTime);

        return reservationDateTime.isAfter(now);
    }

}
