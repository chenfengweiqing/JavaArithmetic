/*
 * Copyright (C) 2018 Jenly Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.lcz.com.qrbarcode.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import org.w3c.dom.css.Rect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class CodeUtils {

    private CodeUtils() {
        throw new AssertionError();
    }

//    /**
//     * 解析二维码图片
//     *
//     * @param bitmapPath
//     * @return
//     */
//    public static String parseQRCode(String bitmapPath) {
//        Map<DecodeHintType, Object> hints = new HashMap<>();
//        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
//        return parseQRCode(bitmapPath, hints);
//    }
//
//    /**
//     * 解析二维码图片
//     *
//     * @param bitmapPath
//     * @param hints
//     * @return
//     */
//    public static String parseQRCode(String bitmapPath, Map<DecodeHintType, ?> hints) {
//        Bitmap detectBitmap = compressBitmap(bitmapPath);
//        return parseQRCode(detectBitmap, hints);
//
//    }
//
//    /**
//     * 解析二维码图片
//     *
//     * @param detectBitmap
//     * @return
//     */
//    public static String parseQRCode(Bitmap detectBitmap) {
//        Map<DecodeHintType, Object> hints = new HashMap<>();
//        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
//        return parseQRCode(detectBitmap, hints);
//    }
//
//
//    /**
//     * 解析二维码Bitmap
//     *
//     * @param detectBitmap
//     * @return
//     */
//    public static String parseQRCode(Bitmap detectBitmap, Map<DecodeHintType, ?> hints) {
//        try {
//            QRCodeReader reader = new QRCodeReader();
//            Result result = null;
//            RGBLuminanceSource source = getRGBLuminanceSource(detectBitmap);
//            if (source != null) {
//                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//                try {
//                    result = reader.decode(bitmap, hints);
//                } catch (Exception e) {//解析失败则通过GlobalHistogramBinarizer 再试一次
//                    BinaryBitmap bitmap1 = new BinaryBitmap(new GlobalHistogramBinarizer(source));
//                    try {
//                        result = reader.decode(bitmap1);
//                    } catch (NotFoundException ne) {
//
//                    }
//                } finally {
//                    reader.reset();
//                }
//            }
//            if (result != null) {
//                return result.getText();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return null;
//    }
//
//    /**
//     * 解析一维码/二维码图片
//     *
//     * @param detectBitmap
//     * @return
//     */
//    public static String parseBarCode(Bitmap detectBitmap) {
//        Map<DecodeHintType, Object> hints = new HashMap<>();
//        Vector<BarcodeFormat> decodeFormats = new Vector<>();
//        decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
//        decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
//        decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
//        decodeFormats.addAll(DecodeFormatManager.AZTEC_FORMATS);
//        decodeFormats.addAll(DecodeFormatManager.PDF417_FORMATS);
//        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
//        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
//        try {
//            MultiFormatReader reader = new MultiFormatReader();
//            reader.setHints(hints);
//            Result result = null;
//            RGBLuminanceSource source = getRGBLuminanceSource(compressBitmap(detectBitmap));
//            if (source != null) {
//                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//                try {
//                    result = reader.decode(bitmap);
//                } catch (Exception e) {//解析失败则通过GlobalHistogramBinarizer 再试一次
//                    BinaryBitmap bitmap1 = new BinaryBitmap(new GlobalHistogramBinarizer(source));
//                    try {
//                        result = reader.decode(bitmap1);
//                    } catch (NotFoundException ne) {
//
//                    }
//                } finally {
//                    reader.reset();
//                }
//            }
//            return result.getText();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 解析一维码/二维码图片
//     *
//     * @param bitmapPath
//     * @return
//     */
//    public static String parseCode(String bitmapPath) {
//        Map<DecodeHintType, Object> hints = new HashMap<>();
//        //添加可以解析的编码类型
//        Vector<BarcodeFormat> decodeFormats = new Vector<>();
//        decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
//        decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
//        decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
//        decodeFormats.addAll(DecodeFormatManager.AZTEC_FORMATS);
//        decodeFormats.addAll(DecodeFormatManager.PDF417_FORMATS);
//
//        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
//        return parseCode(bitmapPath, hints);
//    }
//
//    /**
//     * 解析一维码/二维码图片
//     *
//     * @param bitmapPath
//     * @param hints      解析编码类型
//     * @return
//     */
//    public static String parseCode(String bitmapPath, Map<DecodeHintType, Object> hints) {
//        try {
//            MultiFormatReader reader = new MultiFormatReader();
//            reader.setHints(hints);
//            Result result = null;
//            RGBLuminanceSource source = getRGBLuminanceSource(compressBitmap(bitmapPath));
//            if (source != null) {
//                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//                try {
//                    result = reader.decodeWithState(bitmap);
//                } catch (Exception e) {//解析失败则通过GlobalHistogramBinarizer 再试一次
//                    BinaryBitmap bitmap1 = new BinaryBitmap(new GlobalHistogramBinarizer(source));
//                    try {
//                        result = reader.decodeWithState(bitmap1);
//                    } catch (NotFoundException ne) {
//
//                    }
//                } finally {
//                    reader.reset();
//                }
//            }
//            return result.getText();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    /**
//     * 压缩图片
//     *
//     * @param bitmap
//     * @return
//     */
//    private static Bitmap compressBitmap(Bitmap bitmap) {
//        float width = 800f;
//        float height = 480f;
//        if (bitmap.getWidth() > width || bitmap.getHeight() > height) {
//            float scale = Math.max(bitmap.getWidth() / width, bitmap.getHeight() / height);
//            return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() / scale), (int) (bitmap.getHeight() / scale), true);
//        } else {
//            return bitmap;
//        }
//    }
//
//    /**
//     * 压缩图片
//     *
//     * @param path
//     * @return
//     */
//    private static Bitmap compressBitmap(String path) {
//
//        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
//        newOpts.inJustDecodeBounds = true;//获取原始图片大小
//        BitmapFactory.decodeFile(path, newOpts);// 此时返回bm为空
//        int w = newOpts.outWidth;
//        int h = newOpts.outHeight;
//        float width = 800f;
//        float height = 480f;
//        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//        int be = 1;// be=1表示不缩放
//        if (w > h && w > width) {// 如果宽度大的话根据宽度固定大小缩放
//            be = (int) (newOpts.outWidth / width);
//        } else if (w < h && h > height) {// 如果高度高的话根据宽度固定大小缩放
//            be = (int) (newOpts.outHeight / height);
//        }
//        if (be <= 0)
//            be = 1;
//        newOpts.inSampleSize = be;// 设置缩放比例
//        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//        newOpts.inJustDecodeBounds = false;
//        return BitmapFactory.decodeFile(path, newOpts);
//    }
//
//    /**
//     * 获取RGBLuminanceSource
//     *
//     * @param bitmap
//     * @return
//     */
//    private static RGBLuminanceSource getRGBLuminanceSource(@NonNull Bitmap bitmap) {
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//
//        int[] pixels = new int[width * height];
//        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
//        return new RGBLuminanceSource(width, height, pixels);
//
//    }

//    public static PlanarYUVLuminanceSource buildLuminanceSource(byte[] data, int width, int height) {
//        Rect rect = getFramingRectInPreview(width, height);
//        if (rect == null) {
//            return null;
//        }
//        // Go ahead and assume it's YUV rather than die.
//        PlanarYUVLuminanceSource source = null;
//
//        try {
//            source = new PlanarYUVLuminanceSource(data, width, height, rect.left, rect.top,
//                    rect.width(), rect.height(), false);
//        } catch(Exception e) {
//        }
//
//        return source;
//    }

    public static String getQRresult(String filePath) {
        /**
         * 如果用的jdk是1.9，需要配置下面这一行。
         */
        //System.setProperty("java.specification.version", "1.9");
        Result result = null;
        try {
            File file = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(file);
//            PlanarYUVLuminanceSource source = buildLuminanceSource(data, width, height);

//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(compressImage(bufferedImage, 300, 200))));
//            BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            Map<DecodeHintType, Object> hints = new HashMap<>();
            Vector<BarcodeFormat> decodeFormats = new Vector<>();
            decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.AZTEC_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.PDF417_FORMATS);
            hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            MultiFormatReader reader = new MultiFormatReader();
            reader.setHints(hints);
            result = reader.decode(bitmap);
        } catch (NotFoundException e) {
            System.out.println("pickOrderItem: NotFoundException  ");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("pickOrderItem: IOException  ");
            e.printStackTrace();
        }
        if (result != null) {
            return result.getText();
        } else {
            return null;
        }

    }

    /**
     * 处理图片
     *
     * @param src
     * @param new_w
     * @param new_h
     */
    private synchronized static BufferedImage compressImage(BufferedImage src, int new_w, int new_h) {
        // 得到图片
        int old_w = src.getWidth();
        // 得到源图宽
        int old_h = src.getHeight();
        // 得到源图长
        BufferedImage newImg = null;
        // 判断输入图片的类型
        System.out.println("pickOrderItem: getType  " + src.getType());
        switch (src.getType()) {
            case 13:
                //   png,gif
                newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_4BYTE_ABGR);
                break;
            default:
                newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
                break;
        }
        Graphics2D g = newImg.createGraphics();
        // 从原图上取颜色绘制新图
        g.drawImage(src, 0, 0, old_w, old_h, null);
        g.dispose();
        // 根据图片尺寸压缩比得到新图的尺寸
        newImg.getGraphics().drawImage(
                src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0,
                null);
        // 调用方法输出图片文件
        return newImg;
    }


    /**
     * 图片文件读取
     *
     * @param srcImgPath
     * @return
     */
    private static BufferedImage InputImage(String srcImgPath) {
        BufferedImage srcImage = null;
        try {
            FileInputStream in = new FileInputStream(srcImgPath);
            srcImage = javax.imageio.ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("读取图片文件出错！" + e.getMessage());
            e.printStackTrace();
        }
        return srcImage;
    }

    /**
     * * 将图片按照指定的图片尺寸压缩
     *
     * @param srcImgPath :源图片路径
     * @param outImgPath :输出的压缩图片的路径
     * @param new_w      :压缩后的图片宽
     * @param new_h      :压缩后的图片高
     */
    public static void compressImage(String srcImgPath, String outImgPath,
                                     int new_w, int new_h) {
        BufferedImage src = InputImage(srcImgPath);
        disposeImage(src, outImgPath, new_w, new_h);
    }

    /**
     * 指定长或者宽的最大值来压缩图片
     *
     * @param srcImgPath :源图片路径
     * @param outImgPath :输出的压缩图片的路径
     * @param maxLength  :长或者宽的最大值
     */
    public static void compressImage(String srcImgPath, String outImgPath,
                                     int maxLength) {
        // 得到图片
        BufferedImage src = InputImage(srcImgPath);
        if (null != src) {
            int old_w = src.getWidth();
            // 得到源图宽
            int old_h = src.getHeight();
            // 得到源图长
            int new_w = 0;
            // 新图的宽
            int new_h = 0;
            // 新图的长
            // 根据图片尺寸压缩比得到新图的尺寸
            if (old_w > old_h) {
                // 图片要缩放的比例
                new_w = maxLength;
                new_h = (int) Math.round(old_h * ((float) maxLength / old_w));
            } else {
                new_w = (int) Math.round(old_w * ((float) maxLength / old_h));
                new_h = maxLength;
            }
            disposeImage(src, outImgPath, new_w, new_h);
        }
    }

    /**
     * 处理图片
     *
     * @param src
     * @param outImgPath
     * @param new_w
     * @param new_h
     */
    private synchronized static void disposeImage(BufferedImage src,
                                                  String outImgPath, int new_w, int new_h) {
        // 得到图片
        int old_w = src.getWidth();
        // 得到源图宽
        int old_h = src.getHeight();
        // 得到源图长
        BufferedImage newImg = null;
        // 判断输入图片的类型
        switch (src.getType()) {
            case 13:
                // png,gifnewImg = new BufferedImage(new_w, new_h,
                // BufferedImage.TYPE_4BYTE_ABGR);
                break;
            default:
                newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
                break;
        }
        Graphics2D g = newImg.createGraphics();
        // 从原图上取颜色绘制新图
        g.drawImage(src, 0, 0, old_w, old_h, null);
        g.dispose();
        // 根据图片尺寸压缩比得到新图的尺寸
        newImg.getGraphics().drawImage(
                src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0,
                null);
        // 调用方法输出图片文件
        OutImage(outImgPath, newImg);
    }

    /**
     * 将图片文件输出到指定的路径，并可设定压缩质量
     *
     * @param outImgPath
     * @param newImg
     */
    private static void OutImage(String outImgPath, BufferedImage newImg) {
        // 判断输出的文件夹路径是否存在，不存在则创建
        File file = new File(outImgPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }// 输出到文件流
        try {
            ImageIO.write(newImg,
                    outImgPath.substring(outImgPath.lastIndexOf(".") + 1),
                    new File(outImgPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, String> readfile(String filepath,
                                                Map<Integer, String> pathMap) throws Exception {
        if (pathMap == null) {
            pathMap = new HashMap<Integer, String>();
        }

        File file = new File(filepath);
        // 文件
        if (!file.isDirectory()) {
            pathMap.put(pathMap.size(), file.getPath());

        } else if (file.isDirectory()) { // 如果是目录， 遍历所有子目录取出所有文件名
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(filepath + "/" + filelist[i]);
                if (!readfile.isDirectory()) {
                    pathMap.put(pathMap.size(), readfile.getPath());

                } else if (readfile.isDirectory()) { // 子目录的目录
                    readfile(filepath + "/" + filelist[i], pathMap);
                }
            }
        }
        return pathMap;
    }


}
