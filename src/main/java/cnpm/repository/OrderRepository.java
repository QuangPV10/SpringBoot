package cnpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cnpm.entity.Order;
import cnpm.entity.User;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findByUser(User user);

	@EntityGraph(attributePaths = { "cartItems", "payments", "shipping" })
	Order findEagerById(Long id);

	@Query(value = "SELECT * FROM user_order where user_id = ?1 and order_status= 'In Progress'", nativeQuery = true)
	List<Order> findOrderByUserIdStatusInProgress(long id);

	@Query(value = "SELECT * FROM user_order where user_id = ?1 and order_status= 'To Ship'", nativeQuery = true)
	List<Order> findOrderByUserIdStatusInShip(long id);

	@Query(value = "SELECT * FROM user_order where user_id = ?1 and order_status= 'Done'", nativeQuery = true)
	List<Order> findOrderByUserIdStatusInDone(long id);

	@Query(value = "SELECT * FROM user_order where order_status= 'In Progress'", nativeQuery = true)
	List<Order> findOrderStatusInProgress();

	@Query(value = "SELECT * FROM user_order where order_status= 'To Ship'", nativeQuery = true)
	List<Order> findOrderStatusToShip();

	@Query(value = "SELECT * FROM user_order where order_status= 'Done'", nativeQuery = true)
	List<Order> findOrderStatusToDone();

	@Query(value = "select sum(order_total) as tong from user_order where month(order_date) =?1 and order_status = 'Done' and payment_status ='Paid'", nativeQuery = true)
	String getTotal(int month);

	@Query(value = "select sum(order_total) as tong from user_order where year(order_date) =?1 and order_status = 'Done' and payment_status ='Paid'", nativeQuery = true)
	String getTotalYear(int year);

	@Query(value = "select sum(qty) from (user_order u join cart_item c on u.id = c.order_id)  where  year(u.order_date) = ?1 and u.order_status = 'Done'", nativeQuery = true)
	String getQtyYear(int year);

	@Query(value = "select distinct year(order_date) from user_order where order_status ='Done'", nativeQuery = true)
	List<String> getYear();
}
