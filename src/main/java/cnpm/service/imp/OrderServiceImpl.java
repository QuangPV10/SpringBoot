package cnpm.service.imp;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cnpm.entity.CartItem;
import cnpm.entity.Order;
import cnpm.entity.Payments;
import cnpm.entity.Shipping;
import cnpm.entity.Shoe;
import cnpm.entity.User;
import cnpm.model.ShoppingCart;
import cnpm.repository.CartItemRepository;
import cnpm.repository.OrderRepository;
import cnpm.repository.ShoeRepository;
import cnpm.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	ShoeRepository articleRepository;

	@Override
	@Transactional
	@CacheEvict(value = "itemcount", allEntries = true)
	public synchronized Order createOrder(ShoppingCart shoppingCart, Shipping shipping, Payments payment,
			Optional<User> user) {
		Order order = new Order();
		order.setUser(user.get());
		order.setPayments(payment);
		order.setShipping(shipping);
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shipping.setOrder(order);
		payment.setOrder(order);
		LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate = today.plusDays(5);
		order.setOrderDate(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setShippingDate(Date.from(estimatedDeliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setOrderStatus("In Progress");
		order.setPaymentStatus("Unpaid");

		order = orderRepository.save(order);

		List<CartItem> cartItems = shoppingCart.getCartItems();
		for (CartItem item : cartItems) {
			Shoe shoe = item.getShoe();
			shoe.decreaseStock(item.getQty());
			articleRepository.save(shoe);
			item.setOrder(order);
			cartItemRepository.save(item);
		}
		return order;
	}

	@Override
	@Transactional
	@CacheEvict(value = "itemcount", allEntries = true)
	public synchronized Order createOrderPayPal(ShoppingCart shoppingCart, Shipping shipping, Payments payment,
			Optional<User> user) {
		Order order = new Order();
		order.setUser(user.get());
		order.setPayments(payment);
		order.setShipping(shipping);
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shipping.setOrder(order);
		payment.setOrder(order);
		LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate = today.plusDays(5);
		order.setOrderDate(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setShippingDate(Date.from(estimatedDeliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setOrderStatus("To Ship");
		order.setPaymentStatus("Paid");

		order = orderRepository.save(order);

		List<CartItem> cartItems = shoppingCart.getCartItems();
		for (CartItem item : cartItems) {
			Shoe shoe = item.getShoe();
			shoe.decreaseStock(item.getQty());
			articleRepository.save(shoe);
			item.setOrder(order);
			cartItemRepository.save(item);
		}
		return order;
	}

	@Override
	public Order findOrderWithDetails(Long id) {
		return orderRepository.findEagerById(id);
	}

	public List<Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}

	@Override
	public List<Order> findAllOrder() {
		return (List<Order>) orderRepository.findAll();
	}

	@Override
	public void doneOrder(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		order.get().setOrderStatus("Done");
		order.get().setPaymentStatus("Paid");
		orderRepository.save(order.get());

	}

	@Override
	public List<Order> findOrderStatusInProgress() {

		return orderRepository.findOrderStatusInProgress();
	}

	@Override
	public List<Order> findOrderStatusToShip() {
		return orderRepository.findOrderStatusToShip();
	}

	@Override
	public List<Order> findOrderStatusDone() {
		return orderRepository.findOrderStatusToDone();
	}

	@Override
	public void toShipOrder(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		order.get().setOrderStatus("To Ship");
		orderRepository.save(order.get());

	}

	@Override
	public List<Order> findOrderByUserIdStatusInProgress(Long id) {
		return orderRepository.findOrderByUserIdStatusInProgress(id);
	}

	@Override
	public List<Order> findOrderByUserIdStatusInShip(Long id) {
		return orderRepository.findOrderByUserIdStatusInShip(id);
	}

	@Override
	public List<Order> findOrderByUserIdStatusInDone(Long id) {
		return orderRepository.findOrderByUserIdStatusInDone(id);
	}

	@Override
	public void deleteShoeById(Long id) {
		orderRepository.deleteById(id);

	}

}
