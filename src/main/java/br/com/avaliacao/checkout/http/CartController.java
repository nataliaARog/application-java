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

        CartItem item = new CartItem();
        item.setProduto(p);
        item.setQuantity(q);

        Cart cart = cartDB.findOne(cartId);

        if (cart == null) {
            cart = new Cart();
            cart.setCartId(cartId);
        } //ATENCAO: caso o carrinho ja exista, o fluxo atende?

        cart.getItems().add(item);
        cartDB.save(cart);
    }


}

