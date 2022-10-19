package com.example.finalprojectnectar.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.utilities.Size;

public class MainButton extends AppCompatButton {

    Context mContext;

    public MainButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setTextColor(context.getColor(R.color.white));
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        setTypeface(getResources().getFont(R.font.gilroy_regular));
        setWidth(Size.dpToPx(mContext, 353));
        setHeight(Size.dpToPx(mContext, 67));

        Drawable normal = getDrawable(context.getColor(R.color.main));
        Drawable press = getDrawable(context.getColor(R.color.main_light));
        StateListDrawable states = new StateListDrawable();

        states.addState(new int[]{}, normal);
        states.addState(new int[]{android.R.attr.state_pressed}, press);
        setBackground(states);

    }

    public Drawable getDrawable(int color) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(Size.dpToPx(mContext, 19));
        shape.setColor(color);

        return shape;
    }
}
