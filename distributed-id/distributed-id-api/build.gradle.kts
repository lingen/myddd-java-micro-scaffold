plugins {
    `java-library`
}

group = "org.myddd.java.distributed"
version = rootProject.extra["projectVersion"]!!


dependencies {
    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")
}
