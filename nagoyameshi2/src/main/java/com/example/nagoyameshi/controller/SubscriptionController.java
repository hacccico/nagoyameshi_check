package com.example.nagoyameshi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.repository.UserRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
	@Value("${stripe.api-key}")
	private String stripeApiKey;
	private final UserRepository userRepository;
	private final UserService userService;

	public SubscriptionController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}

	@GetMapping("/add")
	public String index(HttpServletRequest httpServletRequest, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		Stripe.apiKey = stripeApiKey;
		String sessionId = "";
        String requestUrl = new String(httpServletRequest.getRequestURL());

		SessionCreateParams params = SessionCreateParams.builder()
				.setSuccessUrl(requestUrl.replace("/subscription/add", ""))
				.addLineItem(
						SessionCreateParams.LineItem.builder()
								.setPrice("price_1QKc5bBcKXXWFZvo1M6JLQmx")
								.setQuantity(1L)
								.build())
				.putMetadata("id", userDetailsImpl.getUser().getId().toString())
				.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
				.build();
		try {
			Session session = Session.create(params);
			sessionId = session.getId();
		} catch (StripeException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		model.addAttribute("sessionId", sessionId);
		return "subscription/confirm";

        }

	
	@GetMapping("/cancel")
	public String delete(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, RedirectAttributes redirectAttributes) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
	    userService.cancelSubscription(user);
	    
		redirectAttributes.addFlashAttribute("successMessage", "有料プランを解約しました。");
		
		
		return "redirect:/";
	}



}
