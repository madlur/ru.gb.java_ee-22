package ru.geekbrains.persist;

import java.io.Serializable;
import java.math.BigDecimal;

public class Category implements Serializable {

      private Long id;

    private String parentCategory;

    private String categoryName;

    private String categoryDescription;

    private BigDecimal categoryExistence;


    public Category(){}

    public Category(Long id, String parentCategory, String categoryName, String categoryDescription, BigDecimal categoryExistence) {
        this.id = id;
        this.parentCategory = parentCategory;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryExistence = categoryExistence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public BigDecimal getCategoryExistence() {
        return categoryExistence;
    }

    public void setCategoryExistence(BigDecimal categoryExistence) {
        this.categoryExistence = categoryExistence;
    }

}

