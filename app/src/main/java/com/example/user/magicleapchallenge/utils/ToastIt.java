package com.example.user.magicleapchallenge.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastIt {

    Context context;

    public ToastIt(Context context) {
        this.context = context;
    }

    public static ToastIt On(Context context) {

        return new ToastIt(context);
    }

    public void with(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

}
