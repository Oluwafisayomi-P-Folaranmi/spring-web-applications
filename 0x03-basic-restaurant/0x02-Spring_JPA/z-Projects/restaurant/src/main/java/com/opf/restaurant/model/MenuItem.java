package com.opf.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_item")
public class MenuItem {

    /*
     * Fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "inventory")
    private Integer inventory;

    @Column(name = "timestamp")
    private String timeStamp;  // Still represented as String type for now.

    /*
     * Constructors
     */
    public MenuItem() {
    }

    public MenuItem(String title, Double price, Integer inventory, String timeStamp) {
        this.title = title;
        this.price = price;
        this.inventory = inventory;
        this.timeStamp = timeStamp;
    }

    /*
     * Getters and Setters
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /*
     * toString
     */
    @Override
    public String toString() {
        return "MenuItem { " +
                "id='" + id + "', title='" + title + "', price='" + price +
                "', inventory='" + inventory + "', timestamp='" + timeStamp + "'" +
                " }";
    }
}
