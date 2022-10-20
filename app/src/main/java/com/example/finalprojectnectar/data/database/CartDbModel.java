package com.example.finalprojectnectar.data.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_table")
public class CartDbModel {

    @PrimaryKey(autoGenerate = true)
    private Long id = 0L;
    private String title = "";
    private String image = "";
    private double price = 0;
    private int count = 0;



    public CartDbModel(Long id, String title, double price, int count, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public int getCount() {
        return count;
    }
    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }


}
