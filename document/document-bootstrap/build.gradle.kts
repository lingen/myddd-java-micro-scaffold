plugins {
    `java-library`
}

group = "org.myddd.java.document"
version = rootProject.extra["projectVersion"]!!

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {

    implementation(project(":document:document-domain"))
    implementation(project(":document:document-infra"))
    implementation(project(":document:document-api"))
    implementation(project(":document:document-application"))

    implementation("org.springframework.boot:spring-boot-starter-web:${rootProject.extra["spring.boot"]}")

    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-domain:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-ioc-spring:${rootProject.extra["myddd_version"]}")


    implementation("org.apache.dubbo:dubbo:${rootProject.extra["dubbo_version"]}")

    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.0")
    implementation("com.sun.xml.bind:jaxb-core:2.3.0")
    implementation("javax.activation:activation:1.1.1")


    testImplementation("org.springframework.boot:spring-boot-starter-test:${rootProject.extra["spring.boot"]}")
    implementation("com.h2database:h2:${rootProject.extra["h2_version"]}")
    testImplementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")

}
