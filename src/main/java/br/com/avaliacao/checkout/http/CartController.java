package br.com.avaliacao.checkout.http;

import br.com.avaliacao.checkout.db.CartDBInMemory;
import br.com.avaliacao.checkout.model.Produto;
import br.com.avaliacao.checkout.model.Cart;
import br.com.avaliacao.checkout.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartDBInMemory cartDB;

	@RequestMapping(value = "/adicionar")
	public void addToCart(String cartId, Integer q, String codeProduct, String nameProduct, String brand, Double price) {
		Produto p = new Produto();
		p.setCodigo(codeProduct);
		p.setNome(nameProduct);
		p.setMarca(brand);
		p.setPreco(price);
		
		//verificação do item para au,entar ou não a quantidade e se adiciona novo itemao carrinho
		CartItem item = cartDB.findOneItem(p.getCodigo(),cartId);

		if(item.getProduto() != null && item.getProduto().getCodigo() != p.getCodigo()) {
			System.out.println("quantidade antes: "+item.getQuantity());
			item.incrementQuantity(item.getQuantity());
			System.out.println("quantidade depois: "+item.getQuantity());
			
		} else {
			item = new CartItem();
			item.setProduto(p);
			item.setQuantity(q);
		}		

		Cart cart = cartDB.findOne(cartId);

		//ATENCAO: caso o carrinho já exista, o fluxo atende? Esta parte do fluxo atendeu      
		if (cart == null) {
			cart = new Cart();
			cart.setCartId(cartId);
		}        

		cart.getItems().add(item);
		cartDB.save(cart);
	}
}

