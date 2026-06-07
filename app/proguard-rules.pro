# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-verbose

# Optimization is turned off by default. Dontoptimize if you trouble with processing and keep the
# below options in this "on-the-fly" tool. Rather, incorporate these settings into
# your release build script via the External Regions Gradle dsl, or use the Ant task.
-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose

# Preserve some pointers that are required for exceptions
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

# Preserve annotated Javascript interface methods.
-keepclasseswithmembers class * {
    *** *JavascriptInterface(...);
}

# The support libraries contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about all the potential problems.
-dontwarn android.support.**
-dontwarn androidx.**

-keep class androidx.** { *; }
-keep interface androidx.** { *; }
