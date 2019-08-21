//
// Created by liaocanzhong on 2019-08-07.
//
#include <jni.h>
#include <iostream>
#include <zxing/common/Counted.h>
#include <zxing/Binarizer.h>
#include <zxing/MultiFormatReader.h>
#include <zxing/ReaderException.h>
#include <zxing/common/GlobalHistogramBinarizer.h>
#include <zxing/common/HybridBinarizer.h>
#include <zxing/common/GreyscaleLuminanceSource.h>

#include <zxing/qrcode/QRCodeReader.h>
#include <zxing/multi/qrcode/QRCodeMultiReader.h>
#include <zxing/multi/ByQuadrantReader.h>
#include <zxing/multi/GenericMultipleBarcodeReader.h>
#include <zxing/common/StringUtils.h>
#include <syslog.h>
extern "C" {
#include "zbar/zbar_entry.h"
}


using namespace std;
using namespace zxing;
using namespace zxing::multi;
using namespace zxing::scancode;

namespace {

    bool more = false;
}
const static int QRCODE = 1;
const static int BARCODE = 2;
#ifndef SEENGENE_KY_MED_DECODEENTRY_H
#define SEENGENE_KY_MED_DECODEENTRY_H

const char *decodeZxing(int , int , int , int , int , int ,
                        char *);
bool IsUTF8(const void *pBuffer, long size);

#endif //SEENGENE_KY_MED_DECODEENTRY_H
