plugins {
    id("net.mamoe.maven-central-publish") version "0.6.0-dev-2"

    kotlin("jvm") version "1.5.20"
    kotlin("plugin.serialization") version "1.5.20"

    id("net.mamoe.mirai-console") version "2.7-M2"
}

group = "org.itxtech"
version = "2.0.0-beta.1"
description = "强大的 mirai 原生插件加载器。"

kotlin {
    sourceSets {
        all {
            languageSettings.enableLanguageFeature("InlineClasses")
            languageSettings.useExperimentalAnnotation("kotlin.Experimental")
        }
    }
}

repositories {
    mavenCentral()
    maven("https://maven.aliyun.com/repository/public")
}

dependencies {
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
}

mavenCentralPublish {
    singleDevGithubProject("iTXTech", "mirai-native")
    licenseAGplV3()
    useCentralS01()
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Name"] = "iTXTech MiraiNative"
        attributes["Revision"] = Runtime.getRuntime().exec("git rev-parse --short HEAD")
            .inputStream.bufferedReader().readText().trim()
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
