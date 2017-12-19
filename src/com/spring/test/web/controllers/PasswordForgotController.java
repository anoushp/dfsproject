package com.spring.test.web.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.test.web.dao.Mail;
import com.spring.test.web.dao.PasswordForgotForm;
import com.spring.test.web.dao.PasswordResetToken;
import com.spring.test.web.dao.User;
import com.spring.test.web.service.EmailService;
import com.spring.test.web.service.PasswordResetTokensService;
import com.spring.test.web.service.UsersService;



@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {
	@Autowired private UsersService userService;
    @Autowired private PasswordResetTokensService tokenService;
    @Autowired private EmailService emailService;
    
    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotForm forgotPasswordForm() {
        return new PasswordForgotForm();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotForm form,
                                            BindingResult result,
                                            HttpServletRequest request) {
        System.out.println("EMAIL"+form.getEmail());
        if (result.hasErrors()){
        	for (Object object : result.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;

					System.out.println(fieldError.getCode());
					System.out.println(fieldError.getField());
				}
			}
        	System.out.println("ERROR");
            return "forgot-password";
        }

        User user = userService.findByEmail(form.getEmail());
        if (user == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgot-password";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenService.save(token);

        Mail mail = new Mail();
        mail.setFrom("anoushp@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://memorynotfound.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);

        return "redirect:/forgot-password?success";

    }

}
