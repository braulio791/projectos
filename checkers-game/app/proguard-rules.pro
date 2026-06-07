# ProGuard rules for Checkers Game

# Keep all classes and members (for debugging)
-keep class com.braulio791.checkers.** { *; }

# Keep Android classes
-keep class android.** { *; }
-keep class androidx.** { *; }

# Keep Kotlin metadata
-keep class kotlin.** { *; }
-keepclassmembers class kotlin.** { *; }

# Remove logging in release builds
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}
