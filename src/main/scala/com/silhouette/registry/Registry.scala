package com.silhouette.registry

import java.nio.file.Path
import com.silhouette.project.ProjectPath
import com.silhouette.project.ProjectLocalPath
import com.silhouette.project.Project

import scala.collection.mutable._

// Registry has a location (folder)
// Registry contains a list of projects and templates
// Registry can be loaded, to make projects and templates available

// Either create a registry class, or create a registry collector?

class Registry {
    var projects: Map[String, Project] = Map.empty[String, Project]

    def registerProject(proj: Project): Unit =
        projects(java.util.UUID.randomUUID.toString) = proj

}

def collectRegistryProjectDetails(projPath: ProjectPath) =
    // iterate over path finding .slh.json files
    // Recursively
    // Parse into ProjectDetails objects
    projPath match
        case ProjectLocalPath(path) =>
            findLocalProjects(path)
def findLocalProject(path: os.Path): Option[Project] =
    return ProjectLocalPath(path).readProject()

def findLocalProjects(path: os.Path): List[Project] =
    val projects: List[Project] = os.list(path).filter(os.isDir).flatMap(findLocalProjects).toList
    findLocalProject(path).getOrElse(null) :: projects