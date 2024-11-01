package com.silhouette.registry

import java.nio.file.Path
import com.silhouette.project.ProjectPath
import com.silhouette.project.ProjectLocalPath
import com.silhouette.project.Project

import scala.collection.mutable._
import com.silhouette.project.ProjectTemplate

// Registry has a location (folder)
// Registry contains a list of projects and templates
// Registry can be loaded, to make projects and templates available

// Either create a registry class, or create a registry collector?

def getUUID(): String =
    java.util.UUID.randomUUID.toString

class Registry {
    var projects: Map[String, Project] = Map.empty[String, Project]
    var projectTemplates: Map[String, ProjectTemplate] = Map.empty[String, ProjectTemplate]

    def registerProject(proj: Project): Unit =
        projects(getUUID()) = proj
    
    def assignProjectIds(projs: List[Project]): List[(String, Project)] =
        projs.map(p => (getUUID() -> p))
    
    def registerProjects(projs: List[Project]): Unit =
        projects.addAll(assignProjectIds(projs))

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
    val projects: List[Project] =
        os.list(path).filter(os.isDir).flatMap(findLocalProjects).toList
    findLocalProject(path).getOrElse(null) :: projects
