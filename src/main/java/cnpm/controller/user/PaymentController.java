package cnpm.controller.user;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import cnpm.entity.Address;
import cnpm.entity.Order;
import cnpm.entity.Payments;
import cnpm.entity.Shipping;
import cnpm.entity.User;
import cnpm.model.ShoppingCart;
import cnpm.paypal.PaypalPaymentIntent;
import cnpm.paypal.PaypalPaymentMethod;
import cnpm.paypal.PaypalService;
import cnpm.paypal.Utils;
import cnpm.service.OrderService;
import cnpm.service.ShoppingCartService;
import cnpm.service.UserService;

@Controller
public class PaymentController {

	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaypalService paypalService;

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/paypal")
	public String index(Model model, Principal principal) {
		String un = principal.getName();
		Optional<User> user = userService.findByUsername(un);
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user.get());
		model.addAttribute("shoppingCart", shoppingCart);
		model.addAttribute("cartItemList", shoppingCart.getCartItems());
		return "paypal";
	}

	@PostMapping("/pay")
	public String pay(HttpServletRequest request, @RequestParam("price") double price) {
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(price, "USD", PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/paypal";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay() {
		return "cancel";
	}

	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
			Model model, Principal principal) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);

			if (payment.getState().equals("approved")) {

				String un = principal.getName();
				Optional<User> user = userService.findByUsername(un);

				ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user.get());
				Address address = new Address(payment.getPayer().getPayerInfo().getShippingAddress().getLine1(),
						payment.getPayer().getPayerInfo().getShippingAddress().getCity(),
						payment.getPayer().getPayerInfo().getShippingAddress().getCountryCode());
				String receiver = payment.getPayer().getPayerInfo().getLastName();
				Shipping shipping = new Shipping(receiver, address);

				String holderName = payment.getPayer().getPayerInfo().getLastName()
						+ payment.getPayer().getPayerInfo().getFirstName();
				String cardNumber = payment.getPayer().getPayerInfo().getShippingAddress().getPostalCode();
				String cardName = payment.getPayer().getPayerInfo().getShippingAddress().getRecipientName();

				Payments pm = new Payments("Paypal", cardName, cardNumber, holderName);

				Order order = orderService.createOrderPayPal(shoppingCart, shipping, pm, user);
				model.addAttribute("order", order);
				return "success";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/paypal";
	}

}