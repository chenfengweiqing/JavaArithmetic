package test.lcz.com.qrbarcode;

import test.lcz.com.qrbarcode.zxing.CodeUtils;

/**
 * @Author: 廖灿中
 * @Email: liaocanzhong@seengene.com
 * @Date: 2019-08-06
 */
public class CodeScan {

    public static void main(String[] args) {
        String r128 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/128.gif");
        System.out.println("pickOrderItem: r128  " + r128);
        String cose11 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/code-11.gif");
        System.out.println("pickOrderItem: cose11  " + cose11);
        String code39all = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/code-39-all.gif");
        System.out.println("pickOrderItem: code39all  " + code39all);
        String code93 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/code-93.gif");
        System.out.println("pickOrderItem: code93  " + code93);
        String code39 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/code_39.gif");
        System.out.println("pickOrderItem: code39  " + code39);
        String code_2of5 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/coe-25.gif");
        System.out.println("pickOrderItem: code_2of5  " + code_2of5);
        String gsi128 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/gsi-128.gif");
        System.out.println("pickOrderItem: gsi128  " + gsi128);
        String msi = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/msi.gif");
        System.out.println("pickOrderItem: msi  " + msi);
        String qrcode = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/qrcode.png");
        System.out.println("pickOrderItem: qrcode  " + qrcode);
        String sap_product_eight = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/sap_product_eight.png");
        System.out.println("pickOrderItem: sap_product_eight  " + sap_product_eight);
        String telepen = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/telepen-alpha.gif");
        System.out.println("pickOrderItem: telepen  " + telepen);
        String test = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/test.png");
        System.out.println("pickOrderItem: test  " + test);
        String test200 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/test200.png");
        System.out.println("pickOrderItem: test200  " + test200);
        String test400 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/test400.png");
        System.out.println("pickOrderItem: test400  " + test400);
        String test800 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/test800.png");
        System.out.println("pickOrderItem: test800  " + test800);
        String tests800 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/tests800.png");
        System.out.println("pickOrderItem: tests800  " + tests800);
        String big = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/big.png");
        System.out.println("pickOrderItem: big  " + big);
        String base128 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/base128.png");
        System.out.println("pickOrderItem: base128  " + base128);
        String small128 = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/small128.png");
        System.out.println("pickOrderItem: small128  " + small128);
        String small128jpg = CodeUtils.getQRresult("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/qrbarcode/barcode/small128.jpg");
        System.out.println("pickOrderItem: small128jpg  " + small128jpg);
    }
}
