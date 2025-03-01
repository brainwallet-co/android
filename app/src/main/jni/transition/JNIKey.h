
#ifndef BRAINWALLET_JNIKEY_H
#define BRAINWALLET_JNIKEY_H

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jbyteArray JNICALL Java_com_jniwrappers_BRKey_compactSign(JNIEnv *env, jobject thiz,
                                                                    jbyteArray data);

JNIEXPORT jboolean JNICALL Java_com_jniwrappers_BRKey_setPrivKey(JNIEnv *env,
                                                                 jobject thiz,
                                                                 jbyteArray privKey);

JNIEXPORT void JNICALL Java_com_jniwrappers_BRKey_setSecret(JNIEnv *env,
                                                            jobject thiz,
                                                            jbyteArray privKey);

JNIEXPORT jbyteArray JNICALL Java_com_jniwrappers_BRKey_encryptNative(JNIEnv *env, jobject thiz,
                                                                      jbyteArray data,
                                                                      jbyteArray nonce);

JNIEXPORT jbyteArray JNICALL Java_com_jniwrappers_BRKey_decryptNative(JNIEnv *env, jobject thiz,
                                                                      jbyteArray data,
                                                                      jbyteArray nonce);

JNIEXPORT jbyteArray JNICALL Java_com_jniwrappers_BRKey_address(JNIEnv *env, jobject thiz);

#ifdef __cplusplus
}
#endif

#endif //BRAINWALLET_JNIKEY_H
