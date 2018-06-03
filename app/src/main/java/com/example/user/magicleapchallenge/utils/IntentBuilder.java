package com.example.user.magicleapchallenge.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class IntentBuilder {

    public static IntentBuilder.Creator createWith(Context context) {
        return new IntentBuilder.Creator(context);
    }

    public static class Creator {

        Intent intent;
        ComponentName componentName;
        Context context;

        Creator(Context context) {
            this.context = context;
            intent = new Intent();
        }

        public Creator setTarget(Class c) {
            componentName = new ComponentName(context, c);
            intent.setComponent(componentName);
            return this;
        }

        public Creator addExtra(String key, String extra) {
            intent.putExtra(key, extra);
            return this;
        }

        public void startActivity() {
            context.startActivity(intent);

        }

    }

}
