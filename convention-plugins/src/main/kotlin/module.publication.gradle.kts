import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`
import java.util.Properties

plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(
            tasks.register("${name}JavadocJar", Jar::class) {
                archiveClassifier.set("javadoc")
                archiveAppendix.set(this@withType.name)
            },
        )

        // Provide artifacts information required by Maven Central
        pom {
            name.set("KMULE - Kotlin Multiplatform Utilities Library for Everyone")
            description.set("KMULE is a set of utilities for Kotlin Multiplatform projects.")
            url.set("https://github.com/ipirangad3v/kmule")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("kmule")
                    name.set("thondigital")
                    organization.set("thondigital")
                    organizationUrl.set("https://thon.digital")
                }
            }
            scm {
                url.set("https://github.com/ipirangad3v/kmule")
            }
        }
    }
}

signing {

    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    } else {
        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")

        if (localPropertiesFile.exists()) {
            localPropertiesFile.inputStream().use {
                localProperties.load(it)
            }
        }
        val signingKey: String = localProperties.getProperty("signing.key")
        val signingPassword: String = localProperties.getProperty("signing.password")
        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(publishing.publications)
    }
}