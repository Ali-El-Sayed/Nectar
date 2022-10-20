package com.example.finalprojectnectar.data.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalprojectnectar.R;

@Entity(tableName = "fav_table")
public class FavDb {

    @PrimaryKey(autoGenerate = true)
    private Long id = 0L;
    private String title = "";
    private int price = 0;
    private String image = "";

    public FavDb(Long id, String title, int price, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
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

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

}
