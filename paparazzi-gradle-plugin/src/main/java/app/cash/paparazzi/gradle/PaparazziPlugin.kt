/*
 * Copyright (C) 2019 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.cash.paparazzi.gradle

import app.cash.paparazzi.VERSION
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class PaparazziPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    require(project.plugins.hasPlugin("com.android.library")) {
      "The Android Gradle library plugin must be applied before the Paparazzi plugin."
    }

    project.configurations.getByName("testImplementation").dependencies.add(
        project.dependencies.create("app.cash.paparazzi:paparazzi:$VERSION")
    )

    val variants = project.extensions.getByType(LibraryExtension::class.java)
        .libraryVariants
    variants.all { variant ->
      val variantSlug = variant.name.capitalize()

      val writeResourcesTask = project.tasks.register(
          "preparePaparazzi${variantSlug}Resources", PrepareResourcesTask::class.java
      ) {
        // TODO: variant-aware file path
        it.outputs.file("${project.buildDir}/intermediates/paparazzi/resources.txt")

        // Temporary, until AGP provides outputDir as Provider<File>
        it.mergeResourcesProvider = variant.mergeResourcesProvider
        it.outputDir = project.layout.buildDirectory.dir("intermediates/paparazzi/resources.txt")
        it.dependsOn(variant.mergeResourcesProvider)
      }

      project.tasks.named("test${variant.unitTestVariant.name.capitalize()}")
          .configure {
            it.dependsOn(writeResourcesTask)
          }
    }
  }
}