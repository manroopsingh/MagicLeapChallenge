package com.example.user.magicleapchallenge.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static String Tag(Object object) {
        return object.getClass().getSimpleName() + "_TAG";
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
