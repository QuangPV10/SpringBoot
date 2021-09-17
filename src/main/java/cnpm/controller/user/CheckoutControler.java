package cnpm.controller.user;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cnpm.entity.Address;
import cnpm.entity.Order;
import cnpm.entity.Payments;
import cnpm.entity.Shipping;
import cnpm.entity.User;
import cnpm.model.ShoppingCart;
import cnpm.service.OrderService;
import cnpm.service.ShoppingCartService;
import cnpm.service.UserService;

@Controller
public class CheckoutControler {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private UserService service;

	@Autowired
	private OrderService orderService;

	@RequestMapping("/checkout")
	public String checkout(@RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField,
			Model model, Principal principal) {
//		User user = (User) authentication.getPrincipal();
		String un = principal.getName();
		Optional<User> user = service.findByUsername(un);

		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user.get());
		if (shoppingCart.isEmpty()) {
			model.addAttribute("emptyCart", true);
			return "redirect:/shopping-cart/cart";
		}
		model.addAttribute("cartItemList", shoppingCart.getCartItems());
		model.addAttribute("shoppingCart", shoppingCart);
		if (missingRequiredField) {
			model.addAttribute("missingRequiredField", true);
		}
		return "checkout";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String placeOrder(@ModelAttribute("shipping") Shipping shipping, @ModelAttribute("address") Address address,
			@ModelAttribute("payments") Payments payment, RedirectAttributes redirectAttributes, Principal principal) {
		String un = principal.getName();
		Optional<User> user = service.findByUsername(un);

		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user.get());
		if (!shoppingCart.isEmpty()) {
			shipping.setAddress(address);
			Order order = orderService.createOrder(shoppingCart, shipping, payment, user);
			redirectAttributes.addFlashAttribute("order", order);
		}
		return "redirect:/order-submitted";
	}

	@RequestMapping(value = "/order-submitted", method = RequestMethod.GET)
	public String orderSubmitted(Model model) {
		Order order = (Order) model.asMap().get("order");
		if (order == null) {
			return "redirect:/";
		}
		model.addAttribute("order", order);
		return "orderSubmitted";
	}

	@RequestMapping("/order-detail")
	public String orderDetail(@RequestParam("order") Long id, Model model) {
		Order order = orderService.findOrderWithDetails(id);
		model.addAttribute("order", order);
		return "orderDetails";
	}

	@GetMapping(value = "/quan-ly-don-hang")
	public String getOrderHistoryPage(Model model, Principal principal) {

		String un = principal.getName();

		Optional<User> user = service.findByUsername(un);
		user.get().getId();
		long id = user.get().getId();
		model.addAttribute("ordersInProgress", orderService.findOrderByUserIdStatusInProgress(id));
		model.addAttribute("ordersInShip", orderService.findOrderByUserIdStatusInShip(id));
		model.addAttribute("ordersInDone", orderService.findOrderByUserIdStatusInDone(id));

		return "ordersmanagement";
	}

}
