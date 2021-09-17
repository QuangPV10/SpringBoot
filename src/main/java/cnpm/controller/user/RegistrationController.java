package cnpm.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cnpm.entity.Token;
import cnpm.entity.User;
import cnpm.repository.TokenRepository;
import cnpm.repository.UserRepository;
import cnpm.service.EmailService;
import cnpm.service.UserService;

@Controller
public class RegistrationController {

	public static final String NOTE = "Đăng Ký";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private EmailService emailService;

	@PostMapping("/check-username")
	@ResponseBody
	public String checkUserNamePost(@RequestParam String username) {
		return userRepository.findNameUser(username) != null ? "exist" : "oke";

	}

	@PostMapping("/check-email")
	@ResponseBody
	public String checkEmaiPost(@RequestParam String email) {
		return userRepository.findByEmail(email) != null ? "exist" : "oke";

	}

	@GetMapping(value = "/dang-ky")
	public ModelAndView displayRegistration(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@PostMapping(value = "/dang-ky")
	public ModelAndView registerUser(ModelAndView modelAndView, User user) {

		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null) {
			modelAndView.addObject("message", "This email already exists!");
			modelAndView.setViewName("error");
		} else {
			userService.saveWithDefaulRole(user);

			Token confirmationToken = new Token(user, NOTE);

			tokenRepository.save(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("chand312902@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());

			emailService.sendEmail(mailMessage);
			modelAndView.addObject("email", user.getEmail());
			modelAndView.setViewName("sentmailsuccess");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
		Token token = tokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			User user = userRepository.findByEmail(token.getUser().getEmail());
			user.setEnabled(true);
			userRepository.save(user);
			modelAndView.setViewName("accountVerified");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}

		return modelAndView;
	}

}