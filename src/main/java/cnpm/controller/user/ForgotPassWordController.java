package cnpm.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cnpm.entity.Token;
import cnpm.entity.User;
import cnpm.repository.TokenRepository;
import cnpm.repository.UserRepository;
import cnpm.service.EmailService;

@Controller
public class ForgotPassWordController {

	public static final String NOTE = "Quên Mật Khẩu";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping(value = "/quen-mat-khau")
	public ModelAndView displayForgotPassword(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("forgetpassword");
		return modelAndView;
	}

	@PostMapping(value = "/quen-mat-khau")
	public ModelAndView forgotUserPassword(ModelAndView modelAndView, User user) {
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null) {
			Token confirmToken = new Token(existingUser, NOTE);

			tokenRepository.save(confirmToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(existingUser.getEmail());
			mailMessage.setSubject("Complete Password Reset!");
			mailMessage.setFrom("test-email@gmail.com");
			mailMessage.setText("To complete the password reset process, please click here: "
					+ "http://localhost:8080/confirm-reset?token=" + confirmToken.getConfirmationToken());

			emailService.sendEmail(mailMessage);
			modelAndView.addObject("email", user.getEmail());
			modelAndView.addObject("message",
					"Request to reset password received. Check your inbox for the reset link.");
			modelAndView.setViewName("sentmailsuccess");

		} else {
			modelAndView.addObject("message", "This email address does not exist!");
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/confirm-reset", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
		Token token = tokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			User user = userRepository.findByEmail(token.getUser().getEmail());
			userRepository.save(user);
			modelAndView.addObject("user", user);
			modelAndView.setViewName("resetpassword");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	@PostMapping(value = "/reset-password")
	public ModelAndView resetUserPassword(ModelAndView modelAndView, User user) {

		if (user.getEmail() != null) {
			User tokenUser = userRepository.findByEmail(user.getEmail());
			tokenUser.setPassword(encoder.encode(user.getPassword()));
			userRepository.save(tokenUser);
			modelAndView.addObject("message",
					"Password successfully reset. You can now log in with the new password.");
			modelAndView.setViewName("successResetPassword");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

}
