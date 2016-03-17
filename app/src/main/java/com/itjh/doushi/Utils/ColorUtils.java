package com.itjh.doushi.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.TypedValue;

import com.itjh.doushi.R;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-03-11
 * Time: 11:02
 * FIXME
 */
public class ColorUtils {
    /**
     * Obtains the current theme's primary color.
     * Will default to Color.BLUE.
     *
     * @param context
     *            The current context
     * @return current theme's primary color
     */
    public static int getThemePrimaryColor(final Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(
                typedValue.data,
                new int[] { R.attr.colorPrimary}
        );
        int color = a.getColor(0, Color.BLUE);
        a.recycle();

        return color;
    }

    /**
     * Obtains the current theme's secondary color.
     * Will default to Color.CYAN.
     *
     * @param context
     *            The current context
     * @return current theme's secondary color
     */
    public static int getThemeAccentColor(final Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(
                typedValue.data,
                new int[] { R.attr.colorAccent}
        );
        int color = a.getColor(0, Color.CYAN);
        a.recycle();

        return color;
    }


    /**
     * Lightens a color by a given factor.
     *
     * @param color
     *            The color to lighten
     * @param factor
     *            The factor to lighten the color. 0 will make the color unchanged. 1 will make the
     *            color white.
     * @return lighter version of the specified color.
     */
    public static int lighten(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }
}
