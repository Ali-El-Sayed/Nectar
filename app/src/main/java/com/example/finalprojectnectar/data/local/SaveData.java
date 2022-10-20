package com.example.finalprojectnectar.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class SaveData {
    SharedPreferences mSharedPreferences;

    public SaveData(@NonNull Context context) {
        mSharedPreferences = context.getSharedPreferences("myData", Context.MODE_PRIVATE);
    }

    public void updateUserData(boolean status) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("status", status);
        editor.apply();
    }

    public boolean getUserStatus() {
        return mSharedPreferences.getBoolean("status", false);
    }

}
