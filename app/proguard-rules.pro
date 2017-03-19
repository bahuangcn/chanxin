# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#=====================================================================================

#=====================================================================================
# For Apptimize
-keep class com.apptimize.** { *; }
-keepclassmembers class * extends com.apptimize.ApptimizeTest {
    <methods>;
}

-keep class android.support.v4.view.ViewPager
-keepclassmembers class android.support.v4.view.ViewPager$LayoutParams { *; }
-keep class android.support.v4.app.Fragment { *; }

-keep class com.mixpanel.android.mpmetrics.MixpanelAPI { *; }
-keep class com.google.android.gms.analytics.Tracker { *; }
-keep class com.google.analytics.tracking.android.Tracker { *; }
-keep class com.flurry.android.FlurryAgent { *; }
-keep class com.omniture.AppMeasurementBase { *; }
-keep class com.adobe.adms.measurement.ADMS_Measurement { *; }
-keep class com.adobe.mobile.Analytics { *; }
-keep class com.adobe.mobile.Config { *; }
-keep class com.localytics.android.Localytics { *; }
-keep class com.amplitude.api.AmplitudeClient { *; }
-keep class com.amplitude.api.Revenue { *; }
#=====================================================================================
