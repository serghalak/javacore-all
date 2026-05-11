#include "v2ch13_jnidemo_HelloNative.h"
#include <stdio.h>

JNIEXPORT jint JNICALL Java_v2ch13_jnidemo_HelloNative_printf(
        JNIEnv *env, jclass cl, jstring jstr) {
    const char *cstr = (*env)->GetStringUTFChars(env, jstr, NULL);
    int numchars = printf(cstr);
    (*env)->ReleaseStringUTFChars(env, jstr, cstr);
    return numchars;
}
