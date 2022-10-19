package com.example.finalprojectnectar.utilities;

import android.content.Context;

public class Size {

    public static int pxToDp(final Context context, final int px) {
        return (int)(px / context.getResources().getDisplayMetrics().density);
    }

    public static int dpToPx(final Context context, final int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
