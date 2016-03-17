package com.itjh.doushi.Utils;

import android.content.Context;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-03-01
 * Time: 18:20
 * FIXME
 */
public class ScreenUtils {
    /**
     * @Description: 获取屏幕的宽度
     * @param: context
     * @return: int
     */
    public static int getScreenWidth(final Context context) {
        if (context == null)
            return 0;
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param ctx
     * @return
     */
    public static int getScreenHeight(Context ctx) {
        if (ctx == null)
            return 0;
        return ctx.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
