package ru.geekbrains.controller;

import ru.geekbrains.persist.Order;
import ru.geekbrains.persist.OrderRepository;
import ru.geekbrains.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class OrderController implements Serializable {

    @Inject
    private OrderRepository orderRepository;

    private Order order;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public String editOrder(Order order) {
        this.order = order;
        return "/orders.xhtml?faces-redirect=true";
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order.getId());
    }

    public String createOrder() {
        this.order = new Order();
        return "/orders.xhtml?faces-redirect=true";
    }

    public String saveOrder() {
        if (order.getId() != null) {
            orderRepository.update(order);
        } else {
            orderRepository.insert(order);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public void addToCart(Product product) {
        Order order = new Order();
        order.setId(null);
        order.setDescription(product.getDescription());
        order.setName(product.getName());
        order.setPrice(product.getPrice());
        orderRepository.insert(order);
    }

}
