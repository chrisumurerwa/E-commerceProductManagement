package org.example.productmanagement.Model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Product {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setPrice(double price) { this.price = price; }

    public void setDescription(String description) { this.description = description; }

    public void setCategory(Category category) { this.category = category; }
}
