import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.useIR = true
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
}

group = "ch.imgajeed"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}
