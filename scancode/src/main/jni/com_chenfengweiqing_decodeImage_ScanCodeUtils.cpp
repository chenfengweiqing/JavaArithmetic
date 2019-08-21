//
// Created by liaocanzhong on 2019-08-07.
//
#include <android/log.h>
#include "DecodeEntry.h"
#include <jni.h>

#define LOG_TAG "ScanCode"
#define LOGD(...) ((void)__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__))
extern "C" {
JNIEXPORT jstring JNICALL
Java_com_seengene_scancode_ScanCodeUtils_decodeFromJNI(JNIEnv *env, jobject thiz,
                                                       jint decodeCode,
                                                       jbyteArray data,
                                                       jint dataWidth,
                                                       jint dataHeight,
                                                       jint left, jint top,
                                                       jint width, jint height) {
    char *buffer = (char *) env->GetByteArrayElements(data, JNI_FALSE);
    jstring s = NULL;
    if ((decodeCode & QRCODE) == QRCODE) {
        const char *result = decodeZxing(dataWidth, dataHeight, left, top, width, height,
                                         buffer);
        if (result != NULL && !IsUTF8(result, strlen(result))) {
            env->ReleaseByteArrayElements(data, (jbyte *) buffer, 0);
            return NULL;
        }
        s = env->NewStringUTF(result);
    }
    if (s == NULL && ((decodeCode & BARCODE) == BARCODE)) {
        char *resultBar = decodeZbar(dataWidth, dataHeight, left, top, width, height, buffer);
        s = env->NewStringUTF(resultBar);
    }
    env->ReleaseByteArrayElements(data, (jbyte *) buffer, 0);
    return s;
}

JNIEXPORT jstring JNICALL
Java_com_seengene_scancode_ScanCodeUtils_decodeFileFromJNI(JNIEnv *env, jobject thiz,
                                                           jint decodeCode,
                                                           jintArray pixels,
                                                           jint width, int height) {
    int *pixelsData = env->GetIntArrayElements(pixels, JNI_FALSE);
    char *yuv = new char[width * height];
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            int rgb = pixelsData[i * width + j];
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;
            if (r == g && g == b) {
                yuv[i * width + j] = (byte) r;
            } else {
                yuv[i * width + j] = (byte) ((r + g + g + b) >> 2);
            }
        }
    }
    jstring s = NULL;
    if ((decodeCode & QRCODE) == QRCODE) {
        const char *result = decodeZxing(width, height, 0, 0, width, height, yuv);
        if (result != NULL && !IsUTF8(result, strlen(result))) {
            goto end;
        }
        s = env->NewStringUTF(result);
        if (s == NULL) {
            char *rotateData = new char[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    rotateData[(height - y) * width + width - x] = yuv[x + y * width];
                }
            }
            const char *result = decodeZxing(width, height, 0, 0, width, height, rotateData);
            free(rotateData);
            if (result != NULL && !IsUTF8(result, strlen(result))) {
                goto end;
            }
            s = env->NewStringUTF(result);
        }
    }
    if (s == NULL && ((decodeCode & BARCODE) == BARCODE)) {
        char *resultBar = decodeZbar(width, height, 0, 0, width, height, yuv);
        s = env->NewStringUTF(resultBar);
    }
    end:
    env->ReleaseIntArrayElements(pixels, pixelsData, JNI_FALSE);
    free(yuv);
    return s;
}
}
