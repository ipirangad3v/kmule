import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`
import java.util.Properties

plugins {
    `maven-publish`
    signing
}

ext["secretKey"] = null
ext["signingPassword"] = null

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(
            tasks.register("${name}JavadocJar", Jar::class) {
                archiveClassifier.set("javadoc")
                archiveAppendix.set(this@withType.name)
            }
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
    getSecrets()
    useInMemoryPgpKeys(getExtraString("secretKey"), getExtraString("signingPassword"))
    sign(publishing.publications)
}

fun getExtraString(name: String) = ext[name]?.toString()

fun getSecrets() {
    val secretPropsFile = project.rootProject.file("local.properties")
    if (secretPropsFile.exists()) {
        secretPropsFile.reader().use {
            Properties().apply {
                load(it)
            }
        }.onEach { (name, value) ->
            ext[name.toString()] = value
        }
        ext["secretKey"] = "ADD GPG KEY HERE"
    } else {
        ext["secretKey"] = System.getenv("SIGNING_SECRET_KEY")
        ext["signingPassword"] = System.getenv("SIGNING_PASSWORD")
    }
}
