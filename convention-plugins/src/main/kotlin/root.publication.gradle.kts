import gradle.kotlin.dsl.accessors._b6bea14fb88fd11e46d6fb1ebe601eab.ext
import java.util.Properties

plugins {
    id("io.github.gradle-nexus.publish-plugin")
}

ext["ossrhUsername"] = null
ext["ossrhPassword"] = null

allprojects {
    group = "digital.thon"
    version = "0.0.3"
}

nexusPublishing {

    importSecrets()
    val localProperties = Properties()
    val localPropertiesFile = project.rootProject.file("local.properties")

    // Configure maven central repository
    // https://github.com/gradle-nexus/publish-plugin#publishing-to-maven-central-via-sonatype-ossrh
    repositories {
        sonatype { // only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            if (localPropertiesFile.exists()) {
                localPropertiesFile.inputStream().use {
                    localProperties.load(it)
                    this.username.set(localProperties.getProperty("ossrhUsername"))
                    this.password.set(localProperties.getProperty("ossrhPassword"))
                }
            }
        }
    }
}

fun importSecrets() {
    val secretPropsFile = project.rootProject.file("local.properties")
    if (secretPropsFile.exists()) {
        secretPropsFile.reader().use {
            Properties().apply {
                load(it)
            }
        }.onEach { (name, value) ->
            ext[name.toString()] = value
        }
    } else {
        ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
        ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
    }
}
