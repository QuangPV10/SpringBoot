package cnpm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cnpm.service.OrderService;

@Controller
@RequestMapping("admin")
@PreAuthorize("hasAuthority('Admin')")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/orders-management")
	public String showOrderPage(Model model) {
		model.addAttribute("listorder", orderService.findOrderStatusInProgress());
		return "admin/ordersmanagement";
	}

	@GetMapping("/orders-toship")
	public String showOrderToShipPage(Model model) {
		model.addAttribute("listorder", orderService.findOrderStatusToShip());
		return "admin/orderstoship";
	}

	@GetMapping("/orders-done")
	public String showOrderToDonePage(Model model) {
		model.addAttribute("listorder", orderService.findOrderStatusDone());
		return "admin/ordersdone";
	}

	@GetMapping("/set-done-order/{id}")
	public String setDoneForOrder(@PathVariable Long id, Model model) {
		orderService.doneOrder(id);
		return "redirect:/admin/orders-toship";
	}

	@GetMapping("/set-to-ship/{id}")
	public String setToShipForOrder(@PathVariable Long id, Model model) {
		orderService.toShipOrder(id);
		return "redirect:/admin/orders-management";
	}

	@GetMapping("/delete-order/{id}")
	public String deleteProduct(@PathVariable Long id) {

		orderService.deleteShoeById(id);
		return "redirect:/admin/orders-management";
	}

}
