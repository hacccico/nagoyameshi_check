package com.example.nagoyameshi.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	@NotBlank(message = "予約の日時を選択してください。")
	private String reservedDateTime;
	
	@NotNull(message = "予約人数を入力してください。")
	@Min(value = 1, message = "予約人数は１人以上に設定してください。")
	private Integer numberOfPeople;
	

}
