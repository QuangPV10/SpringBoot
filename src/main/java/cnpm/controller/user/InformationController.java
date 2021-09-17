package cnpm.controller.user;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cnpm.entity.User;
import cnpm.repository.UserRepository;
import cnpm.service.UserService;

@Controller
public class InformationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping(value = "/thay-doi-mat-khau")
	public String getProfile(Model model, Principal principal) {
		String un = principal.getName();
		Optional<User> user = userRepository.findByUsername(un);
		model.addAttribute("user", user);

		return "changepass";
	}

	@GetMapping(value = "/tai-khoan")
	public String updateInformation(Model model, Principal principal) {
		String un = principal.getName();
		Optional<User> user = userRepository.findByUsername(un);
		model.addAttribute("user", user.get());

		return "updateInformation";
	}

	@PostMapping("/cap-nhat-tai-khoan")
	public String updateProfile(@ModelAttribute("updateProfile") User userEntity, Principal principal,
			RedirectAttributes ra, Model model) {
		String un = principal.getName();
		Optional<User> user = userRepository.findByUsername(un);

		user.get().setFullname(userEntity.getFullname());
		user.get().setPhone(userEntity.getPhone());
		user.get().setGender(userEntity.getGender());

		userRepository.save(user.get());
		ra.addFlashAttribute("message1", "UPDATE SUCCESS");
		return "redirect:/tai-khoan";
	}

	@PostMapping("/cap-nhat-mat-khau")
	public String processChangePassword(HttpServletRequest request, HttpServletResponse response, Model model,
			RedirectAttributes ra, @AuthenticationPrincipal Authentication authentication, Principal principal)
			throws ServletException {
		String un = principal.getName();
		Optional<User> user = userRepository.findByUsername(un);

		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");

		model.addAttribute("pageTitle", "Change Expired Password");

		if (oldPassword.equals(newPassword)) {
			ra.addFlashAttribute("message", "Your new password must be different than the old one.");
			return "redirect:/thay-doi-mat-khau?sameoldpasss";
		}

		if (!encoder.matches(oldPassword, user.get().getPassword())) {
			ra.addFlashAttribute("message", "Your old password is incorrect.");
			return "redirect:/thay-doi-mat-khau?oldpasswrong";

		} else {
			userService.changePassword(user, newPassword);
			request.logout();
			ra.addFlashAttribute("message", "You have changed your password successfully. " + "Please login again.");

			return "redirect:/dang-nhap";
		}

	}

}
