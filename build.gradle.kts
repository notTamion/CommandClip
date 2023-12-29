plugins {
    id("java")
    id("maven-publish")
    id("signing")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

group = "io.github.nottamion"
version = "1.0.0-SNAPSHOT"

object Meta {
    const val desc = "A Command Library for Paper"
    const val license = "MIT"
    const val githubRepo = "notTamion/CommandClip"
    const val release = "https://s01.oss.sonatype.org/service/local/"
    const val snapshot = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "commandclip"
            version = project.version.toString()
            from(components["java"])
            pom {
                name.set(project.name)
                description.set(Meta.desc)
                url.set("https://github.com/${Meta.githubRepo}")
                licenses {
                    license {
                        name.set(Meta.license)
                        url.set("https://opensource.org/license/mit")
                    }
                }
                developers {
                    developer {
                        id.set("Tamion")
                        name.set("Tamion")
                    }
                }
                scm {
                    url.set("https://github.com/${Meta.githubRepo}")
                    connection.set("scm:git:git://github.com/${Meta.githubRepo}.git")
                    developerConnection.set("scm:git:git://github.com/${Meta.githubRepo}.git")
                }
                issueManagement {
                    url.set("https://github.com/${Meta.githubRepo}/issues")
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri(Meta.release))
            snapshotRepositoryUrl.set(uri(Meta.snapshot))
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}