plugins {
    java
    `maven-publish`
}

group = "de.tamion"
version = "1.0.0-dev"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "de.tamion"
            artifactId = "CommandClip"
            version = "1.0.0"

            from(components["java"])
        }
    }
}
