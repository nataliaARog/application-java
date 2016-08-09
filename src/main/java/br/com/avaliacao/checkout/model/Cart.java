package br.com.avaliacao.checkout.model;

import java.util.ArrayList;

public class Cart {

    public enum CartStatus {OPENED, ACCOMPLISHED, ABANDONED}

    private String cartId;

    private ArrayList<CartItem> items;

    private CartStatus status = CartStatus.OPENED;

    public String getCartId() {
        return this.cartId;
    }

    public CartStatus getStatus() {
        return this.status;
    }

    public void setCartId(final String cartId) {
        this.cartId = cartId;
    }

    public void setItems(final ArrayList<CartItem> items) {
        this.items = items;
    }

    public void setStatus(final CartStatus status) {
        this.status = status;
    }

    public ArrayList<CartItem> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public boolean isOpened() {
        return CartStatus.OPENED.equals(this.status);
    }

    public boolean isAccomplished() {
        return CartStatus.ACCOMPLISHED.equals(this.status);
    }

    public void accomplished() {
        this.status = CartStatus.ACCOMPLISHED;
    }

    public Double getPrice() {
        Double total = .0;
        for (int i=0; i < items.size(); i++) {
            total += items.get(i).getPrice();
        }
        return total;
    }

}
