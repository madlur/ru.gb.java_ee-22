package ru.geekbrains.persist;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
@Named
public class OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;


    public OrderRepository() {
    }

    @Transactional
    public void insert(Order order) {
        logger.info("Inserting new order");
        em.persist(order);
    }

    @Transactional
    public void update(Order order) {
        em.merge(order);
    }

    @Transactional
    public void delete(long id) {
        Order order = em.find(Order.class, id);
        if (order != null) {
            em.remove(order);
        }
    }

    public Optional<Order> findById(long id) {
        Order order = em.find(Order.class, id);
        if (order != null) {
            return Optional.of(order);
        }
        return Optional.empty();
    }

    @Transactional
    public List<Order> findByProdName(String name) {
        List<Order> list = em.createQuery("from Order o where o.name = :name").getResultList();
        return list;
    }

    public List<Order> findAll() {
        return em.createQuery("from Order", Order.class)
                .getResultList();
    }


}