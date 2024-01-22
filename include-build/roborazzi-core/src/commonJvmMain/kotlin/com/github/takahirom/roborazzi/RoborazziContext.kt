package com.github.takahirom.roborazzi

import org.junit.runner.Description
import java.io.File

@ExperimentalRoborazziApi
class RoborazziContextImpl {
  private var ruleOverrideOutputDirectory: String? = null
  private var ruleOverrideRoborazziOptions: RoborazziOptions? = null

  private var ruleOverrideFileProvider: FileProvider? = null
  private var ruleOverrideDescription: Description? = null

  @InternalRoborazziApi
  fun setRuleOverrideOutputDirectory(outputDirectory: String) {
    ruleOverrideOutputDirectory = outputDirectory
  }

  @InternalRoborazziApi
  fun clearRuleOverrideOutputDirectory() {
    ruleOverrideOutputDirectory = null
  }

  @InternalRoborazziApi
  fun setRuleOverrideRoborazziOptions(options: RoborazziOptions) {
    ruleOverrideRoborazziOptions = options
  }

  @InternalRoborazziApi
  fun clearRuleOverrideRoborazziOptions() {
    ruleOverrideRoborazziOptions = null
  }

  @InternalRoborazziApi
  fun setRuleOverrideFileProvider(fileProvider: FileProvider) {
    ruleOverrideFileProvider = fileProvider
  }

  @InternalRoborazziApi
  fun clearRuleOverrideFileProvider() {
    ruleOverrideFileProvider = null
  }

  @InternalRoborazziApi
  fun setRuleOverrideDescription(description: Description) {
    ruleOverrideDescription = description
  }

  @InternalRoborazziApi
  fun clearRuleOverrideDescription() {
    ruleOverrideDescription = null
  }

  @InternalRoborazziApi
  val outputDirectory: String
    get() = ruleOverrideOutputDirectory ?: roborazziSystemPropertyOutputDirectory()

  @InternalRoborazziApi
  val options: RoborazziOptions
    get() = ruleOverrideRoborazziOptions ?: RoborazziOptions()

  // If we don't use Runner and JUnit Rule, we can't use this property.
  @InternalRoborazziApi
  val fileProvider: ((Description, File, String) -> File)?
    get() = ruleOverrideFileProvider

  // If we don't use Runner and JUnit Rule, we can't use this property.
  @InternalRoborazziApi
  val description: Description?
    get() = ruleOverrideDescription

  override fun toString(): String {
    return """
      RoborazziContext(
        ruleOverrideOutputDirectory=$ruleOverrideOutputDirectory,
        ruleOverrideRoborazziOptions=$ruleOverrideRoborazziOptions,
        ruleOverrideFileProvider=$ruleOverrideFileProvider,
        ruleOverrideDescription=$ruleOverrideDescription
      )
    """.trimIndent()
  }
}

@ExperimentalRoborazziApi
var RoborazziContext = RoborazziContextImpl()
  @InternalRoborazziApi
  set

@ExperimentalRoborazziApi
fun provideRoborazziContext() = RoborazziContext