buildscript {

    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.17")
    }
}


plugins {
    java
    `java-library`
    id("org.springframework.boot") version "2.5.3"
}

val projectVersion = "1.0.0-SNAPSHOT"

extra["dubbo_version"] = "3.0.1"
extra["dubbo_serialization_version"] = "2.7.13"
extra["projectVersion"] = projectVersion
extra["slf4jVersion"] = "1.7.30"
extra["spring.boot"] = "2.5.3"
extra["myddd_version"] = "1.3.0-SNAPSHOT"
extra["h2_version"] = "1.4.200"
extra["protobuf-java"] = "3.17.3"
extra["annotation-api"] = "1.3.2"


group = "org.myddd.java.test"
version = extra["projectVersion"]!!


allprojects {
    // don't cache changing modules at all
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    }

    repositories {

        maven {
            setUrl("https://maven.myddd.org/releases/")
        }
        maven {
            setUrl("https://maven.myddd.org/snapshots/")
        }

        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/spring/")
        }

        mavenCentral()
    }
}

repositories {

    maven {
         setUrl("https://maven.myddd.org/releases/")
    }

    maven {
        setUrl("https://maven.myddd.org/snapshots/")
    }

    maven {
        setUrl("https://maven.aliyun.com/repository/public/")
    }
    maven {
        setUrl("https://maven.aliyun.com/repository/spring/")
    }

    mavenCentral()
}




subprojects {
    apply(plugin = "java")
}


tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}



