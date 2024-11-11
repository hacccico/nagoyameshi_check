package com.example.nagoyameshi.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.nagoyameshi.entity.Category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor
public class RestaurantEditForm {
	@NotNull
	private Integer id;
	
	@NotBlank(message = "店舗名を入力してください。")
	private String name;
	
	@NotNull(message = "カテゴリを入力してください。")
	private Category category;
	
	private MultipartFile imageFile;
	
	@NotBlank(message = "店舗情報を入力してください。")
	private String description;
	
	@NotNull(message = "価格帯を入力してください。")
	private String price;
	
	@NotNull(message = "予約上限数を入力してください。")
	@Min(value = 1, message = "予約上限数は1人以上に設定してください。")
	private Integer capacity;
	
	@NotBlank(message = "開店時間を入力してください。")
	private String openingTime;
	
	@NotBlank(message = "閉店時間を入力してください。")
	private String closingTime;
	
	@NotBlank(message = "郵便番号を入力してください。")
	private String postalCode;
	
	@NotBlank(message = "住所を入力してください。")
	private String address;
	
	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNumber;

}
