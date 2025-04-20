package com.kotlinpl.convention

import org.gradle.api.artifacts.VersionCatalog

/**
 * VersionCatalog extensions
 */

fun VersionCatalog.version(projectId: String) = this.findVersion(projectId).get().toString()

fun VersionCatalog.getLibrary(projectId: String) = this.findLibrary(projectId).get()
