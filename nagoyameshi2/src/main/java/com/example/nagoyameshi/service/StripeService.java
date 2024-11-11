package com.example.nagoyameshi.service;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionRetrieveParams;

@Service
public class StripeService {
	@Value("${stripe.api-key}")
	private String stripeApiKey;
	
	private final ReservationService reservationService;
	
	public StripeService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	
	//セッションから予約情報を取得し、ReservationServiceクラスを介してデータベースに登録する
	public void processSessionCompleted(Event event) {
		Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject();
		optionalStripeObject.ifPresentOrElse(stripeObject -> { 
			Session session = (Session)stripeObject;
			SessionRetrieveParams params = SessionRetrieveParams.builder().addExpand("payment_intent").build();
			
			try {
				session = Session.retrieve(session.getId(), params, null);
				Map<String, String> paymentIntentObject = session.getMetadata();
				String subscriptionId = session.getSubscription();
				
			} catch (StripeException e) {
				e.printStackTrace();
			}
			System.out.println("予約一覧ページの登録処理が成功しました。");
			System.out.println("Stripe API Version:" + event.getApiVersion());
			System.out.println("stripe-java Version:" + Stripe.VERSION);
		},
		() -> {
			System.out.println("予約一覧ページの登録処理が失敗しました。");
			System.out.println("Stripe API Version:" + event.getApiVersion());
			System.out.println("stripe-java Version:" + Stripe.VERSION);
		});
	}

}