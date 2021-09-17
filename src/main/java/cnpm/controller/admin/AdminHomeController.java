package cnpm.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
@PreAuthorize("hasAuthority('Admin')")
public class AdminHomeController {


	@RequestMapping("/home")
	public String charts() {

		return "admin/statistical";
	}

}
