package com.example.finalprojectnectar.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.finalprojectnectar.data.model.Product;

@Database(entities = {FavDb.class}, version = 1)
public abstract class FavouriteDb extends RoomDatabase {
    private static FavouriteDb INSTANCE;
    public abstract IFavouriteDAO Dao();

    public static FavouriteDb getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavouriteDb.class, "fav_database")
                    .fallbackToDestructiveMigration().build();
        return INSTANCE;
    }

}
