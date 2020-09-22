package ru.geekbrains.persist;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 128, nullable = false)
    private String name;

    @Column(length = 1028)
    private String description;

    @Column
    private BigDecimal price;

    public Order() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
