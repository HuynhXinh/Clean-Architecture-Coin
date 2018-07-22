#https://github.com/BranchMetrics/android-branch-deep-linking/blob/master/README.md

-keep class com.google.android.gms.ads.identifier.** { *; }

#In case you are using Facebook SDK to support deep linking through Facebook ads, please make sure to keep the Facebook SDK classes in proguard

#-keep class com.facebook.applinks.** { *; }
#-keepclassmembers class com.facebook.applinks.** { *; }
#-keep class com.facebook.FacebookSdk { *; }