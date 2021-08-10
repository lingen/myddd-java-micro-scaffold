import com.google.protobuf.gradle.*

plugins {
    `java-library`
    id("com.google.protobuf")
}

group = "org.myddd.java.distributed"
version = rootProject.extra["projectVersion"]!!

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {

    implementation(project(":distributed-id:distributed-id-api"))
    implementation(project(":distributed-id:distributed-id-domain"))

    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-query-channel:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-ioc-spring:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-persistence-jpa:${rootProject.extra["myddd_version"]}")

    implementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${rootProject.extra["spring.boot"]}")
    testImplementation("com.h2database:h2:${rootProject.extra["h2_version"]}")
    testImplementation(project(":distributed-id:distributed-id-infra"))

    testImplementation("org.apache.dubbo:dubbo:${rootProject.extra["dubbo_version"]}")
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
