plugins {
    kotlin("jvm") version "1.4.31"
}

group = "ch.imgajeed"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.imgajeed.matrix:Matrix:1.0")
}
