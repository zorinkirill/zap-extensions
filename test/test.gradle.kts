plugins {
    `java-library`
}

dependencies {
    implementation(project(":addOns:pscanrules"))
    implementation(project(":addOns:commonlib"))
    implementation("org.zaproxy:zap:2.11.1")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "my.test.Runner"
    val dependencies = configurations
            .runtimeClasspath
            .get()
            .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies) {
        exclude("META-INF/*.RSA", "META-INF/*.DSA", "META-INF/*.SF")
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
