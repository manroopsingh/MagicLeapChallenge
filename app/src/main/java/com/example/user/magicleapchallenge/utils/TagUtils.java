package com.example.user.magicleapchallenge.utils;

public class TagUtils {
    public static String get(Object object) {
        return object.getClass().getSimpleName() + "_TAG";
    }
}
