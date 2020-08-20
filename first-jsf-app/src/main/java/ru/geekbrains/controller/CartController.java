package ru.geekbrains.controller;

import ru.geekbrains.persist.Cart;
import ru.geekbrains.persist.CartRepository;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


@SessionScoped
@Named
public class CartController implements Serializable {

    @Inject
    private CartRepository cartRepository;

    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Cart> getAllCartProducts() throws SQLException {
        return cartRepository.findAll();
    }

}
