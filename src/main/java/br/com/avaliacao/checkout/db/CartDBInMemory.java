package br.com.avaliacao.checkout.db;

import br.com.avaliacao.checkout.model.Cart;
import br.com.avaliacao.checkout.model.CartItem;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CartDBInMemory {

    private static Map<String, Cart> carts = new HashMap<>();

    public Cart save(Cart cart) {
        carts.put(cart.getCartId(), cart);
        return cart;
    }

    public Cart findOne(String id) {
        return carts.get(id);
    }
    
    //necessário verificar se o item já existe
    public CartItem findOneItem(String codigo,String id) {
    	Cart cart = findOne(id);
    	return cart.getItems().get(Integer.parseInt(codigo)-1) != null ? cart.getItems().get(Integer.parseInt(codigo)-1) : new CartItem();
    }

    public void clear() {
        carts.clear();
    }

	public static Map<String, Cart> getCarts() {
		return carts;
	}

}
