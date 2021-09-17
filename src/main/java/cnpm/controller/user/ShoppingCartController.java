package cnpm.controller.user;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cnpm.entity.CartItem;
import cnpm.entity.Shoe;
import cnpm.entity.User;
import cnpm.model.ShoppingCart;
import cnpm.service.ShoeService;
import cnpm.service.ShoppingCartService;
import cnpm.service.UserService;

@Controller
public class ShoppingCartController {

	@Autowired
	private ShoeService shoeService;

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		String un = principal.getName();
		Optional<User> user = userService.findByUsername(un);
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user.get());
		model.addAttribute("cartItemList", shoppingCart.getCartItems());

		model.addAttribute("shoppingCart", shoppingCart);
		return "cart";
	}

	@RequestMapping("/add-item")
	public String addItem(@ModelAttribute("shoe") Shoe shoe, @RequestParam("qty") String qty,
			@RequestParam("size") String size, RedirectAttributes attributes, Model model,
			Authentication authentication, Principal principal) {
		shoe = shoeService.findShoeById(shoe.getId());
		if (!shoe.hasStock(Integer.parseInt(qty))) {
			attributes.addFlashAttribute("notEnoughStock", true);
			return "redirect:/chi-tiet-san-pham?id=" + shoe.getId();
		}

		String un = principal.getName();
		Optional<User> user = userService.findByUsername(un);

		shoppingCartService.addShoeToShoppingCart(shoe, user, Integer.parseInt(qty), size);
		attributes.addFlashAttribute("addShoeSuccess", true);
		return "redirect:/chi-tiet-san-pham?id=" + shoe.getId();
	}

	@GetMapping("/update-item/{id}/{qty}")
	@ResponseBody
	public void updateItemQuantity(@PathVariable("id") Long cartItemId, @PathVariable("qty") Integer qty, Model model) {
		CartItem cartItem = shoppingCartService.findCartItemById(cartItemId);
		if (cartItem.canUpdateQty(qty)) {
			shoppingCartService.updateCartItem(cartItem, qty);
		}
	}

	@RequestMapping("/remove-item")
	public String removeItem(@RequestParam("id") Long id) {
		shoppingCartService.removeCartItem(shoppingCartService.findCartItemById(id));
		return "redirect:/cart";
	}
}
