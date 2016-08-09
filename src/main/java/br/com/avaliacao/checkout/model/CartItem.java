package br.com.avaliacao.checkout.model;

public class CartItem {

    private Produto produto;

    private Integer quantity;

    public Double getPrice() {
        return quantity * produto.getPreco();
    }

    //?
    public void incrementQuantity(final Integer quantity) {
        this.quantity += quantity;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setProduto(final Produto produto) {
        this.produto = produto;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}