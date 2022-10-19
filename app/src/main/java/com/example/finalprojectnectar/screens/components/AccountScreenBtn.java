package com.example.finalprojectnectar.screens.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.utilities.Size;

public class AccountScreenBtn extends LinearLayout {
    Context mContext;

    public AccountScreenBtn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        this.mContext = context;
        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.AccountScreenBtn);
        String label = attributes.getString(R.styleable.AccountScreenBtn_asb_label);
        int icon = attributes.getResourceId(R.styleable.AccountScreenBtn_asb_icon, 0);

        build(label, icon);

    }

    private void build(String label, int icon) {
        LinearLayout linearH = new LinearLayout(mContext);

        linearH.setOrientation(HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearH.setLayoutParams(layoutParams);
        int padding = 45;
        linearH.setPadding(padding, padding, padding, padding);

        ImageView img = new ImageView(mContext);
        img.setImageResource(icon);
        LayoutParams imgParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParams.gravity = Gravity.CENTER_VERTICAL;
        img.setLayoutParams(imgParams);

        TextView txt = new TextView(mContext);
        txt.setText(label);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        params.leftMargin = 25;
        txt.setLayoutParams(params);
        txt.setTypeface(getResources().getFont(R.font.gilroy_regular));
        txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Size.pxToDp(mContext, 60));
        txt.setTextColor(mContext.getColor(R.color.accountTxtColor));

        ImageView imgArrow = new ImageButton(mContext);
        imgArrow.setLayoutParams( new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imgArrow.setImageResource(R.drawable.ic_arrow_forward);
        imgArrow.setBackgroundColor(Color.TRANSPARENT);

        LinearLayout llh = new LinearLayout(mContext);
        llh.setOrientation(HORIZONTAL);
        llh.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        llh.setGravity(Gravity.END);
        llh.setPadding(0, 0, 30, 0);
        llh.addView(imgArrow);

        linearH.addView(img);
        linearH.addView(txt);

        this.addView(linearH);
        this.addView(llh);
        this.setGravity(Gravity.CENTER_VERTICAL);

    }

}
