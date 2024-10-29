package com.silhouette.registry

import java.nio.file.Path
import com.silhouette.project.ProjectPath
import com.silhouette.project.ProjectLocalPath
import com.silhouette.project.Project

import scala.collection.mutable.Map

// Registry has a location (folder)
// Registry contains a list of projects and templates
// Registry can be loaded, to make projects and templates available

// Either create a registry class, or create a registry collector?

class Registry {
    var projects: Map[String, Project] = Map.empty[String, Project]

    def registerProject(proj: Project): Unit =
        projects(proj.uuid) = proj
}

def collectRegistryProjectDetails(path: ProjectPath, searchLayers: Int) =
    // iterate over path finding .slh.json files
    // Recursively
    // Parse into ProjectDetails objects
    path match
        case ProjectLocalPath(path) =>
