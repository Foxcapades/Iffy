val implementation by configurations
val testImplementation by configurations
val runtimeOnly by configurations
val annotationProcessor by configurations
val testRuntimeOnly by configurations

dependencies {
  // Use JUnit Jupiter API for testing.
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")

  // Use JUnit Jupiter Engine for testing.
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}
