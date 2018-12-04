
plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM
    id("org.jetbrains.kotlin.jvm").version("1.3.10")
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Use the Kotlin JDK 8 standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")


    compile("commons-beanutils:commons-beanutils:1.9.3")
    compile("org.apache.geronimo.specs:geronimo-validation_1.0_spec:1.1")
    compile("org.apache.bval:org.apache.bval.bundle:0.5"){
        // CVE-2014-0114
        exclude(group = "commons-beanutils", module = "commons-beanutils-core")
    }


    // Use the Kotlin test library
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
