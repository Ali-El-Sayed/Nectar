package com.example.finalprojectnectar.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CartDbModel.class}, version = 2)
public abstract class CartDatabase extends RoomDatabase {
    private static CartDatabase INSTANCE;

    public abstract ICartDao Dao();

    public static CartDatabase getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CartDatabase.class, "cart_database")
                    .fallbackToDestructiveMigrationOnDowngrade().build();
        return INSTANCE;
    }


}
