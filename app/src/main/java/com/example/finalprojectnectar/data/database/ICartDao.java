package com.example.finalprojectnectar.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ICartDao {

    @Insert
    void insert(CartDbModel item);

    @Query("SELECT * FROM cart_table")
    LiveData<List<CartDbModel>> getAllProducts();


    @Query("DELETE FROM cart_table WHERE id = :id")
    void deleteById(long id);
}
