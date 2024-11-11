package com.example.nagoyameshi.form;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	@NotNull(message = "予約の日時を選択してください。")
	private LocalDate reservationDate;
	
	@NotNull(message = "予約の日時を選択してください。")
	private LocalTime reservationTime;
	
	@NotNull(message = "予約人数を入力してください。")
	@Min(value = 1, message = "予約人数は１人以上に設定してください。")
	private Integer numberOfPeople;
	

}
