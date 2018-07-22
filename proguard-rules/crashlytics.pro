#https://docs.fabric.io/android/crashlytics/dex-and-proguard.html?highlight=proguard

-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**