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

		Cart cart = cartDB.findOne(cartId);

		//ATENCAO: caso o carrinho já exista, o fluxo atende? Esta parte do fluxo atendeu      
		if (cart == null) {
			cart = new Cart();
			cart.setCartId(cartId);
		}  

		//verificação do item para aumentar ou não a quantidade e se adiciona novo itemao carrinho
		CartItem first = new CartItem();
		first.setProduto(p);
		first.setQuantity(q);
		cart.getItems().add(first);
		if(cartDB.findOne(cartId) == null) {
			cartDB.save(cart);
		}		
		first = cartDB.findFirstItem(cart.getCartId());			
		
		CartItem item = new CartItem();
		if(first != null) {			
			item = cartDB.getNext(cart.getCartId());			
		}		
		
		//se já existe um primeiro item, verificar se o próximo tem o mesmo código do produto
		if(cartDB.isEqual(first, item)) {
			item = cartDB.findOneItem(p.getCodigo(),cart.getCartId());
			item.incrementQuantity(item.getQuantity());
			cart.getItems().remove(Integer.parseInt(item.getProduto().getCodigo()));

		} else {
			item = new CartItem();
			item.setProduto(p);
			item.setQuantity(q);
			cart.getItems().add(item);
		}
		
		cartDB.save(cart);
	}
}

