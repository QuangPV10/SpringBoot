package cnpm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cnpm.service.UserService;

@Controller
@RequestMapping("admin")
@PreAuthorize("hasAuthority('Admin')")
public class AdminUserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users-management")
	public String showUserPage(Model model) {
		model.addAttribute("listuser", userService.findAllUser());
		return "admin/usersmanagement";
	}

	@GetMapping("/set-disable-user/{username}")
	public String setDisableForUser(@PathVariable String username) {
		userService.disableUser(username);
		return "redirect:/admin/users-management";
	}

}
