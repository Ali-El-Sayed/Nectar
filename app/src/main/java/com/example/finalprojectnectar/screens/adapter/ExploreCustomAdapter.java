package com.example.finalprojectnectar.screens.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExploreCustomAdapter {

    private class DataHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public DataHolder(@NonNull View itemView) {

            super(itemView);
        }
    }
}
