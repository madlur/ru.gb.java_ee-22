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
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @Inject
    private ServletContext context;

    private Connection conn;

    public CategoryRepository() {
    }

    @PostConstruct
    public void init() throws SQLException {
        conn =  (Connection) context.getAttribute("connection");
        createTableIfNotExists(conn);

        if (this.findAll().isEmpty()) {
            logger.info("No products in DB. Initializing.");

            this.insert(new Category(-1L, "Digital Goods", "notebooks",
                    "personal computer for mobile use" , new BigDecimal(2567)));
            this.insert(new Category(-1L, "Luxury Goods", "Supercars",
                    "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod",
                    new BigDecimal(45)));
            this.insert(new Category(-1L, "Commodities", "Materials",
                    "goods that are physically almost identical and goods bla bla bla" ,
                    new BigDecimal(120993287)));

        }
    }

    public void insert(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into category(parentCategory, categoryName, categoryDescription, avaliable) values (?, ?, ?, ?);")) {
            stmt.setString(1, category.getParentCategory());
            stmt.setString(2, category.getCategoryName());
            stmt.setString(3, category.getCategoryDescription());
            stmt.setBigDecimal(4, category.getCategoryExistence());
            stmt.execute();
        }
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists category (\n" +
                    "    id int auto_increment primary key,\n" +
                    "    parentCategory varchar(25),\n" +
                    "    categoryName varchar(25),\n" +
                    "    categoryDescription varchar(255),\n" +
                    "    avaliable decimal(12, 2) \n" +
                    ");");
        }
    }

    public List<Category> findAll() throws SQLException {
        List<Category> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, parentCategory, categoryName, categoryDescription, avaliable from category");

            while (rs.next()) {
                res.add(new Category(rs.getLong(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getBigDecimal(5)));
            }
        }
        return res;
    }

    public void update(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update category set parentCategory = ?, categoryName = ?, categoryDescription = ?, avaliable = ? where id = ?;")) {
            stmt.setString(1, category.getParentCategory());
            stmt.setString(2, category.getCategoryName());
            stmt.setString(3, category.getCategoryDescription());
            stmt.setBigDecimal(4, category.getCategoryExistence());
            stmt.setLong(5, category.getId());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from category where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }
}
