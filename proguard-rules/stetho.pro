#https://github.com/facebook/stetho/tree/master/stetho-js-rhino#proguard

# stetho
-keep class com.facebook.stetho.** { *; }

# rhino (javascript)
-dontwarn org.mozilla.javascript.**
-dontwarn org.mozilla.classfile.**
-keep class org.mozilla.javascript.** { *; }