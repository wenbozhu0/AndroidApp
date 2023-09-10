plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.allopen") version "1.9.0"
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))

    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.rest-assured:rest-assured")
    implementation("net.datafaker:datafaker:1.8.1")
    implementation("io.quarkus:quarkus-mongodb-client:3.2.3.Final")
    implementation("io.quarkus:quarkus-mongodb-panache-kotlin:3.2.3.Final")
    implementation("io.quarkus:quarkus-resteasy-jackson")


    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

group = "edu.uchicago.gerber.quark"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.javaParameters = true
}

val quarkusDeployment by configurations.creating
dependencies {
quarkusDeployment("io.quarkus:quarkus-mongodb-panache-common-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-resteasy-jackson-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-swagger-ui-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-vertx-http-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-resteasy-server-common-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-jackson-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-mongodb-client-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-resteasy-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-vertx-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-mutiny-reactive-streams-operators-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-mongodb-panache-kotlin-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-mutiny-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-kotlin-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-narayana-jta-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-netty-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-smallrye-openapi-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-smallrye-context-propagation-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-resteasy-common-deployment:3.2.3.Final")
quarkusDeployment("io.quarkus:quarkus-arc-deployment:3.2.3.Final")
}
typealias PrintWriter = java.io.PrintWriter
typealias FileWriter = java.io.FileWriter
tasks.register("listQuarkusDependencies") {
    val writer = PrintWriter(FileWriter("/var/folders/ld/4q_qwxrx7xg8pg7lb4r8jsq00000gn/T/16480551084550252580.txt"))
    quarkusDeployment.incoming.artifacts.forEach {
        writer.println(it.id.componentIdentifier)
        writer.println(it.file)
    }
    val componentIds = quarkusDeployment.incoming.resolutionResult.allDependencies.map { (it as ResolvedDependencyResult).selected.id }
    val result = dependencies.createArtifactResolutionQuery()
        .forComponents(componentIds)
        .withArtifacts(JvmLibrary::class, SourcesArtifact::class)
        .execute()
    result.resolvedComponents.forEach { component ->
        val sources = component.getArtifacts(SourcesArtifact::class)
        sources.forEach { ar ->
            if (ar is ResolvedArtifactResult) {
                writer.println(ar.id.componentIdentifier)
                writer.println(ar.file)
            }
        }
    }
    writer.close()
}