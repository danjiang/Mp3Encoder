#include <jni.h>
#include <android/log.h>
#include "../../../../../../src/mp3_encoder.h"

#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

#define LOG_TAG "Mp3Encoder"

Mp3Encoder* encoder = NULL;

extern "C"
JNIEXPORT jint JNICALL
Java_com_danthought_mp3encoder_MainActivity_init(
        JNIEnv *env,
        jobject obj,
        jstring pcmPathParam,
        jint channels,
        jint bitRate,
        jint sampleRate,
        jstring mp3PathParam) {
    const char* pcmPath = env->GetStringUTFChars(pcmPathParam, NULL);
    const char* mp3Path = env->GetStringUTFChars(mp3PathParam, NULL);
    LOGI("pcmPath is %s...", pcmPath);
    LOGI("mp3Path is %s...", mp3Path);
    encoder = new Mp3Encoder();
    int ret = encoder->init(pcmPath, mp3Path, sampleRate, channels, bitRate);
    env->ReleaseStringUTFChars(mp3PathParam, mp3Path);
    env->ReleaseStringUTFChars(pcmPathParam, pcmPath);
    return ret;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_danthought_mp3encoder_MainActivity_encode(
        JNIEnv *env,
        jobject obj) {
    if (NULL != encoder) {
        encoder->encode();
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_danthought_mp3encoder_MainActivity_destroy(
        JNIEnv *env,
        jobject obj) {
    if (NULL != encoder) {
        encoder->destroy();
        delete encoder;
        encoder = NULL;
    }
}