package com.example.nagoyameshi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nagoyameshi.repository.UserRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
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

	public SubscriptionController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/add")
	public String index(HttpServletRequest httpServletRequest, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		Stripe.apiKey = stripeApiKey;
		String sessionId = "";
        String requestUrl = new String(httpServletRequest.getRequestURL());

		SessionCreateParams params = SessionCreateParams.builder()
				.setSuccessUrl(requestUrl.replace("/subscription/add", ""))
				.addLineItem(
						SessionCreateParams.LineItem.builder()
								.setPrice("price_1QHiV3BcKXXWFZvoDbFimqA9")
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


}
