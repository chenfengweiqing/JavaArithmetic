package com.seengene.scancode;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * @Author: 廖灿中
 * @Email: liaocanzhong@seengene.com
 * @Date: 2019-08-06
 */
public class ScanCodeUtils {
    public static final String TAG = "ddd";

    static {
        System.loadLibrary("ScanCode");
    }

    public static String getPixelsByBitmap(Bitmap bitmap, BarcodeFormat barcodeFormat) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int size = width * height;

        int pixels[] = new int[size];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        bitmap.recycle();
        if (barcodeFormat != null) {
            return decodeFileFromJNI(barcodeFormat.get(), pixels, width, height);
        } else {
            return decodeFileFromJNI(BarcodeFormat.BARCODE | BarcodeFormat.QRCODE, pixels, width, height);
        }
    }

    public static String getDecodeResult(BarcodeFormat barcodeFormat, byte[] data, int dataWidth,
                                         int dataHeight, int left, int top, int width, int height) {
        if (barcodeFormat != null) {
            return decodeFromJNI(barcodeFormat.get(), data, dataWidth, dataHeight, left, top, width, height);
        } else {
            return decodeFromJNI(BarcodeFormat.BARCODE | BarcodeFormat.QRCODE, data, dataWidth, dataHeight, left, top, width, height);
        }

    }

    public native static String decodeFromJNI(int decodeCode, byte[] data, int dataWidth,
                                              int dataHeight, int left, int top, int width, int height);

    public native static String decodeFileFromJNI(int decodeCode, int[] pixels, int width, int height);

}
