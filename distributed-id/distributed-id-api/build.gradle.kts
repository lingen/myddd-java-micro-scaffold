import com.google.protobuf.gradle.*

plugins {
    `java-library`
    id("com.google.protobuf")
    id("idea")
}

group = "org.myddd.java.distributed"
version = rootProject.extra["projectVersion"]!!


dependencies {

    api("com.google.protobuf:protobuf-java:3.17.3")
    api("javax.annotation:javax.annotation-api:1.3.2")
    api("org.apache.dubbo:dubbo-serialization-protobuf:${rootProject.extra["dubbo_serialization_version"]}")
    implementation("org.apache.dubbo:dubbo-serialization-api") {
        version {
            strictly("${rootProject.extra["dubbo_version"]}")
        }
    }

    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")
}



sourceSets.main {
    proto.srcDir("src/main/protobuf")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${rootProject.extra["protobuf-java"]}"
    }
    plugins {
        id("myddd-dubbo") {
            artifact = "org.myddd.plugin:dubbo-protobuf-gradle-plugin:0.0.5"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("myddd-dubbo")
            }
        }
    }
}

