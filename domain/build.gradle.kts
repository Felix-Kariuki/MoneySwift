plugins {
    id("java-library")
    id("kotlin")


}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    jvmToolchain(17)
}

dependencies {

    implementation(libs.coroutines.core)
    implementation(libs.javax)
    implementation("org.json:json:20240303")

}