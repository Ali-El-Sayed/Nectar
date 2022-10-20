package com.example.finalprojectnectar.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface IFavouriteDAO {
    @Insert
    void insert(FavDb fav);

    @Query("SELECT * FROM fav_table")
    LiveData<List<FavDb>> getAllProducts();

}
