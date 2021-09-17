package cnpm.service;

import java.util.List;
import java.util.Optional;

import cnpm.entity.Order;
import cnpm.entity.Payments;
import cnpm.entity.Shipping;
import cnpm.entity.User;
import cnpm.model.ShoppingCart;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payments payment, Optional<User> user);
	Order createOrderPayPal(ShoppingCart shoppingCart, Shipping shippingAddress, Payments payment, Optional<User> user);

	List<Order> findByUser(User user);

	Order findOrderWithDetails(Long id);

	List<Order> findAllOrder();

	void toShipOrder(Long id);

	void doneOrder(Long id);

	List<Order> findOrderByUserIdStatusInProgress(Long id);

	List<Order> findOrderByUserIdStatusInShip(Long id);

	List<Order> findOrderByUserIdStatusInDone(Long id);

	List<Order> findOrderStatusInProgress();

	List<Order> findOrderStatusToShip();

	List<Order> findOrderStatusDone();

	void deleteShoeById(Long id);
}
