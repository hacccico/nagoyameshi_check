package com.example.nagoyameshi.form;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RestaurantRegisterForm {
	@NotNull
	private Integer id;
	
	@NotBlank(message = "店舗名を入力してください。")
	private String name;
	
	@NotBlank(message = "カテゴリを入力してください。")
	private Integer categoryId;
	
	private MultipartFile imageFile;
	
	@NotBlank(message = "店舗情報を入力してください。")
	private String description;
	
	@NotNull(message = "価格帯を入力してください。")
	private String price;
	
	@NotNull(message = "予約上限数を入力してください。")
	@Min(value = 1, message = "予約上限数は1人以上に設定してください。")
	private Integer capacity;
	
	@NotBlank(message = "開店時間を入力してください。")
	private Timestamp openingTime;
	
	@NotBlank(message = "閉店時間を入力してください。")
	private Timestamp closingTime;
	
	@NotBlank(message = "郵便番号を入力してください。")
	private String postalCode;
	
	@NotBlank(message = "住所を入力してください。")
	private String address;
	
	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNumber;

}
