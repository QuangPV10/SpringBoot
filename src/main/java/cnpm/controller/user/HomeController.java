package cnpm.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cnpm.service.ShoeService;

@Controller
public class HomeController {

	@Autowired
	private ShoeService shoeService;

	@GetMapping("/dang-nhap")
	public String login() {
		return "login";
	}

	@GetMapping(value = { "/", "/trang-chu" })
	public String getHomePage(Model mav) {
		mav.addAttribute("shoeByDate", shoeService.findByTopPrice());
		mav.addAttribute("shoeRandom", shoeService.findByRandom());
		return "index";
	}

	@GetMapping(value = "/lien-lac")
	public String getContactPage() {
		return "contact";
	}

	@GetMapping(value = "/gioi-thieu")
	public String getAboutPage() {
		return "about";
	}

	@GetMapping(value = "/gio-hang")
	public String getCartPage() {
		return "cart";
	}

	@GetMapping(value = "/403")
	public String get403Page() {
		return "403";
	}

}
