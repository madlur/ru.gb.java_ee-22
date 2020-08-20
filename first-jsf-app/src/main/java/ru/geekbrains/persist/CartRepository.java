package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
@Named
public class CartRepository implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(CartRepository.class);

    @Inject
    private ServletContext context;

    private Connection conn;

    public CartRepository() {
    }

    @PostConstruct
    public void init() throws SQLException {
        logger.info("Initializing CartRepo");
        conn = (Connection) context.getAttribute("connection");
        createTableIfNotExists(conn);

        if (this.findAll().isEmpty()) {
            logger.info("No items in DB. Initializing.");

            this.add(new Cart(-1L, "test1", "testtest", new BigDecimal(100)));

        }

    }

    private void add(Cart cart) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into cart(name, description, price) values (?, ?, ?);")) {
            stmt.setString(1, cart.getName());
            stmt.setString(2, cart.getDescription());
            stmt.setBigDecimal(3, cart.getPrice());
            stmt.execute();
        }
    }


    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists cart (\n" +
                    "    id int auto_increment primary key,\n" +
                    "    name varchar(25),\n" +
                    "    description varchar(255),\n" +
                    "    price decimal(12, 2) \n" +
                    ");");
        }
    }

    public List<Cart> findAll() throws SQLException {
        List<Cart> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, name, description, price from cart");

            while (rs.next()) {
                res.add(new Cart(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4)));
            }
        }
        return res;
    }

    public void addtoCart(Cart cart) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into cart(name, description, price) values (?, ?, ?);")) {
            stmt.setString(1, cart.getName());
            stmt.setString(2, cart.getDescription());
            stmt.setBigDecimal(3, cart.getPrice());
            stmt.execute();
        }
    }
}


