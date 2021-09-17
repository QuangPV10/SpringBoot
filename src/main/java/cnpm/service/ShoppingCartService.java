package cnpm.service;

import java.util.Optional;

import cnpm.entity.CartItem;
import cnpm.entity.Shoe;
import cnpm.entity.User;
import cnpm.model.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart getShoppingCart(User user);

	int getItemsNumber(User user);

	CartItem findCartItemById(Long cartItemId);

	CartItem addShoeToShoppingCart(Shoe shoe, Optional<User> user, int qty, String size);

	void clearShoppingCart(User user);

	void updateCartItem(CartItem cartItem, Integer qty);

	void removeCartItem(CartItem cartItem);

}
