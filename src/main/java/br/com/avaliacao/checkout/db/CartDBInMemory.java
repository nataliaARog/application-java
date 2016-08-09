package br.com.avaliacao.checkout.db;

import br.com.avaliacao.checkout.model.Cart;
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

    public void clear() {
        carts.clear();
    }

}
