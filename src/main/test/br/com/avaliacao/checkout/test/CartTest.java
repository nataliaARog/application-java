package br.com.avaliacao.checkout.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.avaliacao.AvaliacaoJavaApplication;
import br.com.avaliacao.checkout.db.CartDBInMemory;
import br.com.avaliacao.checkout.http.CartController;
import br.com.avaliacao.checkout.model.Cart;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AvaliacaoJavaApplication.class)
public class CartTest extends TestCase {
	
	@Autowired
	private CartController cartController;
	
	@Test
	public void test() {		
		
		cartController.addToCart("1", 1, "1", "Sapatilha", "Moleca", 50.00);
		cartController.addToCart("1", 2, "1", "Sapatilha", "Moleca", 50.00);
		cartController.addToCart("1", 1, "2", "Bota", "Moleca", 150.00);
		cartController.addToCart("2", 2, "3", "Sandalha", "Moleca", 80.00);
		
		System.out.println("Se o carrinho existe, não duplica, adiciona normalmente os itens: ");
		
		for(int i=1;i<=CartDBInMemory.getCarts().size();i++) {
			System.out.println("Cart Id: "+new CartDBInMemory().findOne(CartDBInMemory.getCarts().get(Integer.toString(i)).getCartId()).getCartId());
			
			for(int j=0;j<new CartDBInMemory().findOne(CartDBInMemory.getCarts().get(Integer.toString(i)).getCartId()).getItems().size();j++) {
				System.out.println("Item no carrinho (código do produto): "+new CartDBInMemory().findOne(CartDBInMemory.getCarts().get(Integer.toString(i)).getCartId()).getItems().get(j).getProduto().getCodigo());
				System.out.println("Item no carrinho (quantidade de produtos neste item): "+new CartDBInMemory().findOne(CartDBInMemory.getCarts().get(Integer.toString(i)).getCartId()).getItems().get(j).getQuantity());
				System.out.println("Item no carrinho (valor do item): "+new CartDBInMemory().findOne(CartDBInMemory.getCarts().get(Integer.toString(i)).getCartId()).getItems().get(j).getPrice());
			}
		}					
	}

	public static void main(String[] args) {	
		new CartTest().run();
	}

}
