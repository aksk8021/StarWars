package com.example.testprojects.utils;

import android.app.Activity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

public class CommonUtils {
    public static void setUpEdgeToEdge(Activity activity) {
        ViewCompat.setOnApplyWindowInsetsListener(
                activity.findViewById(android.R.id.content),
                (v, windowInsets) -> {
                    Insets insets = windowInsets.getInsets(
                            WindowInsetsCompat.Type.systemBars() |
                                    WindowInsetsCompat.Type.ime());

                    v.setPadding(insets.left, insets.top, insets.right, insets.bottom);
                    return WindowInsetsCompat.CONSUMED;
                }
        );
    }

}

