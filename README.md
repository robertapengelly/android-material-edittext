# Android Lollipop Drawables

Backported material styled EditTet for use on pre-lollipop devices. Supports Android 2.3 API 9 (GINGERBREAD) and up.

Preview

![materialedittextexample](https://cloud.githubusercontent.com/assets/5245027/22003572/99e59cf0-dc4c-11e6-88bb-f3997a27b5ff.gif)

# Installation

    Step 1. Add the JitPack repository to your build file
    
    Add it in your root build.gradle at the end of repositories:
    
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
         }
    }
    
    Step 2. Add the dependency
    
    dependencies {
        compile 'com.github.robertapengelly:android-lollipop-drawables:1.0.0'
    }

# Usage

    Styling
    
    Pre-Honycomb devices (values/styles.xml)
    
    The following style attributes are material defaults for the theme. Change the values to better suit your app.
    
        <style name="AppTheme" parent="@android:style/Theme.NoTitleBar">
            <item name="android:textColorPrimary">@color/primary_text_default_material_dark</item>
            <item name="android:textColorPrimaryInverse">@color/primary_text_default_material_light</item>
            <item name="android:textColorSecondary">@color/secondary_text_default_material_dark</item>
            <item name="android:textColorSecondaryInverse">@color/secondary_text_default_material_light</item>
            <item name="android:textColorTertiary">@color/secondary_text_default_material_dark</item>
            <item name="android:textColorTertiaryInverse">@color/secondary_text_default_material_light</item>
            <item name="android:textColorHighlight">@color/highlighted_text_material_dark</item>
            <item name="android:textColorLink">@color/link_text_material_dark</item>
            
            <item name="colorAccent">@color/accent_material_dark</item>
            <item name="colorControlActivated">?attr/colorAccent</item>
            <item name="colorControlNormal">?android:attr/textColorSecondary</item>
            <item name="editTextColor">?android:attr/textColorPrimary</item>
        </style>
    
    Honycomb and newer (values-v11/styles.xml)
    
    The following style attributes are material defaults for the theme. Change the values to better suit your app.
    
        <style name="AppTheme" parent="@android:style/Theme.Holo.NoActionBar">
            <item name="android:textColorPrimary">@color/primary_text_default_material_dark</item>
            <item name="android:textColorPrimaryInverse">@color/primary_text_default_material_light</item>
            <item name="android:textColorSecondary">@color/secondary_text_default_material_dark</item>
            <item name="android:textColorSecondaryInverse">@color/secondary_text_default_material_light</item>
            <item name="android:textColorTertiary">@color/secondary_text_default_material_dark</item>
            <item name="android:textColorTertiaryInverse">@color/secondary_text_default_material_light</item>
            <item name="android:textColorHighlight">@color/highlighted_text_material_dark</item>
            <item name="android:textColorHighlightInverse">@color/highlighted_text_material_light</item>
            <item name="android:textColorLink">@color/link_text_material_dark</item>
            <item name="android:textColorLinkInverse">@color/link_text_material_light</item>
            
            <item name="colorAccent">@color/accent_material_dark</item>
            <item name="colorControlActivated">?attr/colorAccent</item>
            <item name="colorControlNormal">?android:attr/textColorSecondary</item>
            <item name="android:editTextColor">?android:attr/textColorPrimary</item>
        </style>
    
    Lollipop and newer (values-v21/styles.xml)
    
    The following style attributes are lollipop defaults for the theme. Change the values to better suit your app.
    
        <style name="AppTheme" parent="@android:style/Theme.Material.NoActionBar">
            <item name="android:textColorPrimary">@color/primary_text_default_material_dark</item>
            <item name="android:textColorPrimaryInverse">@color/primary_text_default_material_light</item>
            <item name="android:textColorSecondary">@color/secondary_text_default_material_dark</item>
            <item name="android:textColorSecondaryInverse">@color/secondary_text_default_material_light</item>
            <item name="android:textColorTertiary">@color/secondary_text_default_material_dark</item>
            <item name="android:textColorTertiaryInverse">@color/secondary_text_default_material_light</item>
            <item name="android:textColorHighlight">@color/highlighted_text_material_dark</item>
            <item name="android:textColorHighlightInverse">@color/highlighted_text_material_light</item>
            <item name="android:textColorLink">@color/link_text_material_dark</item>
            <item name="android:textColorLinkInverse">@color/link_text_material_light</item>
            
            <item name="android:colorAccent">@color/accent_material_dark</item>
            <item name="android:colorControlActivated">?android:attr/colorAccent</item>
            <item name="android:colorControlNormal">?android:attr/textColorSecondary</item>
            <item name="android:editTextColor">?android:attr/textColorPrimary</item>
        </style>
    
    Adding a MaterialEditText widget (layout/activity_main.xml)
    
        <?xml version="1.0" encoding="utf-8" ?>
        <LinearLayout
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_height="match_parent"
            android:layout_width="match_parent"
            android:orientation="vertical">
            
                <robertapengelly.support.widget.MaterialEditText
                    android:hint="MaterialEditText"
                    android:layout_height="wrap_content"
                    android:layout_width="match_parent" />
                
                </LinearLayout>
