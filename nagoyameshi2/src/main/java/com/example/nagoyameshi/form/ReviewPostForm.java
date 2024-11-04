package com.example.nagoyameshi.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPostForm {
private Integer id;
	
	private Integer restaurantId;
	
	private Integer userId;
	
	private String reviewStar;
	
	private String reviewComment;

}
