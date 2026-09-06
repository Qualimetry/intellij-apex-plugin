plugins {
    id("java")
    id("org.jetbrains.intellij.platform") version "2.2.1"
}

group = "com.qualimetry.intellij"
version = providers.gradleProperty("pluginVersion").getOrElse("1.9.2")

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    mavenLocal()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2024.3")
    }

    implementation("com.qualimetry.sonar:apex-analyzer:1.9.18")
    implementation("org.yaml:snakeyaml:2.2")

    implementation("org.sonarsource.api.plugin:sonar-plugin-api:11.1.0.2693")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
}

intellijPlatform {
    pluginConfiguration {
        id = "com.qualimetry.apex"
        name = "Qualimetry Apex Analyzer"
        version = project.version.toString()
        description = """
            <p>Static analysis of Salesforce Apex source files (<code>.cls</code>, <code>.trigger</code>)
            in IntelliJ IDEA and Qodana.</p>
            <p>Powered by the same engine as the Qualimetry Apex Analyzer for VS Code and SonarQube.</p>
            <ul>
              <li>280+ rules covering code quality, security, performance, and Salesforce best practices</li>
              <li>Real-time analysis as you edit</li>
              <li>Works in IntelliJ IDEA and JetBrains Qodana</li>
            </ul>
        """.trimIndent()
        vendor {
            name = "Qualimetry"
            url = "https://qualimetry.com"
        }
        ideaVersion {
            sinceBuild = "243"
            untilBuild = provider { null }
        }
    }

    publishing {
        token = providers.environmentVariable("JETBRAINS_MARKETPLACE_TOKEN")
    }

    signing {
        certificateChain = providers.environmentVariable("PLUGIN_SIGNING_CERTIFICATE_CHAIN")
        privateKey = providers.environmentVariable("PLUGIN_SIGNING_KEY")
        password = providers.environmentVariable("PLUGIN_SIGNING_KEY_PASSWORD")
    }
}

tasks {
    buildSearchableOptions {
        enabled = false
    }
}
