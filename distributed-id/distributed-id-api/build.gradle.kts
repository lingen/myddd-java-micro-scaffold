plugins {
    `java-library`
}

group = "org.myddd.java.distributed"
version = rootProject.extra["projectVersion"]!!


dependencies {

    api("com.google.protobuf:protobuf-java:3.17.3")
    api("io.grpc:grpc-protobuf:1.39.0")
    api("io.grpc:grpc-stub:1.39.0")
    api("javax.annotation:javax.annotation-api:1.3.2")
    
    implementation("org.apache.dubbo:dubbo-serialization-protobuf:2.7.13")

    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")
}
