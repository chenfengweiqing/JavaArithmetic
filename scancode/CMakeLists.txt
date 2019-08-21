# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.0)

file(GLOB sources src/main/jni/*.cpp
        src/main/jni/bigint/*.cc
        src/main/jni/zxing/*.cpp
        src/main/jni/zxing/aztec/*.cpp
        src/main/jni/zxing/aztec/decoder/*.cpp
        src/main/jni/zxing/aztec/detector/*.cpp
        src/main/jni/zxing/common/*.cpp
        src/main/jni/zxing/common/detector/*.cpp
        src/main/jni/zxing/common/reedsolomon/*.cpp
        src/main/jni/zxing/datamatrix/*.cpp
        src/main/jni/zxing/datamatrix/decoder/*.cpp
        src/main/jni/zxing/datamatrix/detector/*.cpp
        src/main/jni/zxing/multi/*.cpp
        src/main/jni/zxing/multi/qrcode/*.cpp
        src/main/jni/zxing/multi/qrcode/detector/*.cpp
        src/main/jni/zxing/oned/*.cpp
        src/main/jni/zxing/pdf417/*.cpp
        src/main/jni/zxing/pdf417/decoder/*.cpp
        src/main/jni/zxing/pdf417/decoder/ec/*.cpp
        src/main/jni/zxing/pdf417/detector/*.cpp
        src/main/jni/zxing/qrcode/*.cpp
        src/main/jni/zxing/qrcode/decoder/*.cpp
        src/main/jni/zxing/qrcode/detector/*.cpp
        src/main/jni/decoder/*.c
        src/main/jni/video/*.c
        src/main/jni/window/*.c
        src/main/jni/zbar/*.c
        src/main/jni/DecodeEntry.cpp

        src/main/jni/*.h
        src/main/jni/bigint/*.h
        src/main/jni/zxing/*.h
        src/main/jni/zxing/aztec/*.h
        src/main/jni/zxing/aztec/decoder/*.h
        src/main/jni/zxing/aztec/detector/*.h
        src/main/jni/zxing/common/*.h
        src/main/jni/zxing/common/detector/*.h
        src/main/jni/zxing/common/reedsolomon/*.h
        src/main/jni/zxing/datamatrix/*.h
        src/main/jni/zxing/datamatrix/decoder/*.h
        src/main/jni/zxing/datamatrix/detector/*.h
        src/main/jni/zxing/multi/*.h
        src/main/jni/zxing/multi/qrcode/*.h
        src/main/jni/zxing/multi/qrcode/detector/*.h
        src/main/jni/zxing/oned/*.h
        src/main/jni/zxing/pdf417/*.h
        src/main/jni/zxing/pdf417/decoder/*.h
        src/main/jni/zxing/pdf417/decoder/ec/*.h
        src/main/jni/zxing/pdf417/detector/*.h
        src/main/jni/zxing/qrcode/*.h
        src/main/jni/zxing/qrcode/decoder/*.h
        src/main/jni/zxing/qrcode/detector/*.h
        src/main/jni/decoder/*.h
        src/main/jni/video/*.h
        src/main/jni/window/*.h
        src/main/jni/zbar/*.h
        src/main/jni/DecodeEntry.h
        )

add_library(ScanCode SHARED ${sources} src/main/jni/com_chenfengweiqing_decodeImage_ScanCodeUtils.cpp
        )
include_directories(src/main/jni/)

find_library(log-lib log)
target_link_libraries(ScanCode ${log-lib})
