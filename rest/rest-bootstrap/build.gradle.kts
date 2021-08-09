plugins {
    `java-library`
}

group = "org.myddd.java.document"
version = rootProject.extra["projectVersion"]!!

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

val localBuild = if(project.hasProperty("local")) project.property("local") as String == "true" else true

dependencies {

    implementation(project(":document:document-api"))

    implementation("org.springframework.boot:spring-boot-starter-web:${rootProject.extra["spring.boot"]}")

    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-domain:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-ioc-spring:${rootProject.extra["myddd_version"]}")

    //根据参数来决定是构建成单机模式还是分布式模式
    if(localBuild){
        implementation(project(":rest:rest-local-strategy"))
    }else{
        implementation(project(":rest:rest-micro-strategy"))
    }


    testImplementation("org.springframework.boot:spring-boot-starter-test:${rootProject.extra["spring.boot"]}")
    implementation("com.h2database:h2:${rootProject.extra["h2_version"]}")
    testImplementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")

}
