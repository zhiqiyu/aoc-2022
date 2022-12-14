plugins {
    kotlin("jvm") version "1.7.22"
    application
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("Day10Kt") 
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}
