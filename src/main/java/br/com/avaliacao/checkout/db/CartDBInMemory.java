package br.com.avaliacao.checkout.db;

import br.com.avaliacao.checkout.model.Cart;
import br.com.avaliacao.checkout.model.CartItem;

import org.springframework.stereotype.Component;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Map;

@Component
public class CartDBInMemory {

    private static Map<String, Cart> carts = new HashMap<>();
    private int i = 1;
    
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
    	CartItem item = new CartItem();
    	if(cart.getItems() != null && cart.getItems().size() > 1) {
    		item = cart.getItems().get(Integer.parseInt(codigo));
    	}
    	return item;
    }
    
    public CartItem getNext(String id) {
    	Cart cart = findOne(id);
    	CartItem item;
    	if(i == cart.getItems().size()) {
    		item = new CartItem();
    	} else {
    		item = cart.getItems().get(i);
    	}
    	return item;
    }
    
    //necessário verificar se já possui o primeiro item
    public CartItem findFirstItem(String id) {
    	Cart cart = findOne(id); 
    	CartItem item = new CartItem() ;
    	if(cart.getItems() != null) {
    		item = cart.getItems().get(0);
    	}
    	return item;
    }
    
    //verifica se os itens são iguais
    public boolean isEqual(CartItem first,CartItem next) {
    	boolean t = null != null;    	
    	if(next.getProduto() != null) {
    		if(first.getProduto().getCodigo() == next.getProduto().getCodigo()) {
    			t = true;
    		} else {
    			t = false;
    		}
    	} else {
    		t = false;
    	}    	
    	return t;
    }

    public void clear() {
        carts.clear();
    }

	public static Map<String, Cart> getCarts() {
		return carts;
	}

}
