package com.sample.redditclient.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Px;
import android.support.annotation.RequiresApi;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

import com.sample.redditclient.R;

/**
 * Created by osarapul on 17.11.2017.
 */

public abstract class ActivityUtil {
    private ActivityUtil() throws Exception {
        throw new Exception("Forbidden to instantiate utility class.");
    }

    public static void addFragmentToActivity(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment, @IdRes int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void addFragmentWithSharedElementToActivity(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment, @IdRes int frameId,
            @NonNull View sharedElement, @NonNull String transitionName) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.addSharedElement(sharedElement, transitionName);
        transaction.commit();
    }

    @Px
    public static int getActionBarHeight(@NonNull Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }

    @Px
    public static int getStatusBarHeight(@NonNull Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void hideSystemUI(@NonNull Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
