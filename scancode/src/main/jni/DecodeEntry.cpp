// -*- mode:c++; tab-width:2; indent-tabs-mode:nil; c-basic-offset:2 -*-
/*
 *  Copyright 2010-2011 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file excep t in compliance with the License.
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
#include "DecodeEntry.h"

const char *decodeZxing(int dataWidth, int dataHeight, int left, int top, int width, int height,
                        char *rotateData) {

    try {
        ArrayRef<char> data(rotateData, dataWidth * dataHeight);
        Ref<LuminanceSource> source(
                new GreyscaleLuminanceSource(data, dataWidth, dataHeight, left,
                                             top, width, height));

        Ref<Binarizer> binarizer(new HybridBinarizer(source));
        Ref<BinaryBitmap> image(new BinaryBitmap(binarizer));

        DecodeHints hints(DecodeHints::DEFAULT_QR_HINT);
        MultiFormatReader reader;
        Ref<Result> result(reader.decode(image, hints));
        return result->getText()->getText().c_str();
    }

    catch (zxing::Exception &e) {
    }
    return NULL;
}

bool IsUTF8(const void *pBuffer, long size) {
    bool IsUTF8 = true;
    unsigned char *start = (unsigned char *) pBuffer;
    unsigned char *end = (unsigned char *) pBuffer + size;
    while (start < end) {
        if (*start < 0x80) // (10000000): value less then 0x80 ASCII char
        {
            start++;
        } else if (*start < (0xC0)) // (11000000): between 0x80 and 0xC0 UTF-8 char
        {
            IsUTF8 = false;
            break;
        } else if (*start < (0xE0)) // (11100000): 2 bytes UTF-8 char
        {
            if (start >= end - 1)
                break;
            if ((start[1] & (0xC0)) != 0x80) {
                IsUTF8 = false;
                break;
            }
            start += 2;
        } else if (*start < (0xF0)) // (11110000): 3 bytes UTF-8 char
        {
            if (start >= end - 2)
                break;
            if ((start[1] & (0xC0)) != 0x80 || (start[2] & (0xC0)) != 0x80) {
                IsUTF8 = false;
                break;
            }
            start += 3;
        } else {
            IsUTF8 = false;
            break;
        }
    }
    return IsUTF8;
}









