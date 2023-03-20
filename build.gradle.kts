plugins {
    id("application")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.kafka:kafka-streams:3.4.0")
    implementation("org.apache.kafka:kafka-clients:3.4.0")
    runtimeOnly("org.slf4j:slf4j-nop:1.7.36")
    testImplementation("junit:junit:4.13.2")
}

application {
    mainClass.set("com.linuxacademy.ccdak.streams.StreamsMain")
}

listOf(
    "Streams",
    "Aggregations",
    "Joins",
    "StatelessTransformations",
    "Windowing",
).forEach { app ->
    tasks.register<JavaExec>("run$app") {
        dependsOn(tasks.classes)
        group = "Application"
        description = "Runs the $app application"
        mainClass.set("com.linuxacademy.ccdak.streams.${app}Main")
        classpath = sourceSets["main"].runtimeClasspath
    }
}
