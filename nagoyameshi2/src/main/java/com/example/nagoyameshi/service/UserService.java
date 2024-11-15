package com.example.nagoyameshi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Role;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ResetPasswordForm;
import com.example.nagoyameshi.form.SignupForm;
import com.example.nagoyameshi.form.UserEditForm;
import com.example.nagoyameshi.repository.RoleRepository;
import com.example.nagoyameshi.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import com.stripe.param.SubscriptionCancelParams;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	@Value("${stripe.api-key}")
	private String stripeApiKey;
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	public User create(SignupForm signupForm) {
		User user = new User();
		Role role = roleRepository.findByName("ROLE_GENERAL");
		
		user.setName(signupForm.getName());
		user.setEmail(signupForm.getEmail());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		user.setRole(role);
		user.setEnabled(false);
		
		return userRepository.save(user);
	}
	
	@Transactional
	public void update(UserEditForm userEditForm) {
		User user = userRepository.getReferenceById(userEditForm.getId());
		
		user.setName(userEditForm.getName());
		user.setEmail(userEditForm.getEmail());
		
		userRepository.save(user);
	}
	
	//メールアドレスが登録済みかどうかをチェックする
	public boolean isEmailRegistered(String email) {
		User user = userRepository.findByEmail(email);
		return user != null;
	}
	
	//パスワードとパスワード(確認用)の入力値が一致するかどうかをチェック
	public boolean isSamePassword(String password, String passwordConfirmation) {
		return password.equals(passwordConfirmation);
	}
	
	//ユーザーを有効にする
	@Transactional
	public void enableUser(User user) {
		user.setEnabled(true);
		userRepository.save(user);
	}
	
	//メールアドレスが変更されたかどうかをチェックする
	public boolean isEmailChanged(UserEditForm userEditForm) {
		User currentUser = userRepository.getReferenceById(userEditForm.getId());
		return !userEditForm.getEmail().equals(currentUser.getEmail());
	}
	
	@Transactional
	public void updatePassword(ResetPasswordForm resetPasswordForm) {
		User user = userRepository.getReferenceById(resetPasswordForm.getId());

		user.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword()));

		userRepository.save(user);
	}
	
    @Transactional
    public void cancelSubscription(User user) {
       Stripe.apiKey = stripeApiKey;

		try {
	    	 Subscription resource;
			 resource = Subscription.retrieve(user.getSubscriptionId());
	    	 SubscriptionCancelParams params = SubscriptionCancelParams.builder().build();
	    	 resource.cancel(params);
	         user.setSubscriptionId("");
	         
	         Role role = roleRepository.findByName("ROLE_GENERAL");
	         user.setRole(role);
	         
	         userRepository.save(user);
		} catch (StripeException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }


}
