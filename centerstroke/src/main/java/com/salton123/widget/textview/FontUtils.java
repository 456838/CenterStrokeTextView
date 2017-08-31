package com.salton123.widget.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.Hashtable;

/**
 * User: newSalton@outlook.com
 * Date: 2017/8/25 16:42
 * ModifyTime: 16:42
 * Description:
 */
public class FontUtils {

    private static final String TAG = "FontUtils";

    public enum FontType {
        DIN_MITTELSCHRIFT_ALTERNATE, REFRIGERATOR_DELUXE_HEAVY
    }

//    @Nullable
//    public static Typeface getTypeFace(Context context, FontType fontType) {
//        Typeface typeface;
//        try {
//            switch (fontType) {
//                case DIN_MITTELSCHRIFT_ALTERNATE:
//                    typeface = Typeface.createFromAsset(context.getAssets(), "fonts/DINMittelschrift_Alternate.otf");
//                    break;
//                case REFRIGERATOR_DELUXE_HEAVY:
//                    typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Refrigerator_Deluxe_Heavy.ttf");
//                    break;
//                default:
//                    typeface = null;
//                    break;
//            }
//        } catch (Exception ex) {
//            MLog.error(TAG, "getTypeFace error: " + ex);
//            typeface = null;
//        }
//        return typeface;
//    }



    private static Hashtable<FontType, Typeface> fontCache = new Hashtable<>();     //缓存
    /**
     * Typeface.createFromAsset会产生内存泄露，adb shell dumpsys meminfo com.duowan.makefriends -d >D:/meminfo.txt
     * @param context
     * @param fontType
     * @return
     */
    public static Typeface getTypeFace(Context context, FontType fontType) {
        Typeface typeface = fontCache.get(fontType);
        try {
            if (typeface == null) {
                switch (fontType) {
                    case DIN_MITTELSCHRIFT_ALTERNATE:
                        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/DINMittelschrift_Alternate.otf");
                        break;
                    case REFRIGERATOR_DELUXE_HEAVY:
                        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Refrigerator_Deluxe_Heavy.ttf");
                        break;
                }
                if (typeface!=null){
                    fontCache.put(fontType, typeface);
                }else{
                    Log.i(TAG,"[getTypeFace] can not get font from asset,fonttype="+fontType);
                }
            }
        } catch (Exception e) {
            Log.i(TAG,"[getTypeFace] exception="+e+",fonttype="+fontType);
            return null;
        }
        return typeface;
    }
}
