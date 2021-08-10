import com.google.protobuf.gradle.*

plugins {
    `java-library`
    id("com.google.protobuf")
}

group = "org.myddd.java.document"
version = rootProject.extra["projectVersion"]!!

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {

    implementation(project(":document:document-api"))
    implementation(project(":document:document-domain"))

    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-query-channel:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-ioc-spring:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-persistence-jpa:${rootProject.extra["myddd_version"]}")

    implementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")
    implementation("com.google.protobuf:protobuf-java:3.17.3")
    implementation("io.grpc:grpc-protobuf:1.39.0")
    implementation("io.grpc:grpc-stub:1.39.0")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    implementation("org.apache.dubbo:dubbo-serialization-protobuf:2.7.13")


    testImplementation("org.springframework.boot:spring-boot-starter-test:${rootProject.extra["spring.boot"]}")
    testImplementation("com.h2database:h2:${rootProject.extra["h2_version"]}")
    testImplementation(project(":document:document-infra"))
    testImplementation(project(":distributed-id:distributed-id-application"))

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
            artifact = "org.myddd.plugin:dubbo-grpc-gradle-plugin:0.0.4"
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
