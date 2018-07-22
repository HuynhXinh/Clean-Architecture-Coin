## https://github.com/wasabeef/glide-transformations/blob/master/transformations/proguard-rules.txt

-dontwarn jp.co.cyberagent.android.gpuimage.**

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}