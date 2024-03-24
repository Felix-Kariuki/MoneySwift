// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.daggerHilt) apply false
    alias(libs.plugins.kspPlugin) apply false
    alias(libs.plugins.ktlintPlugin)
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
