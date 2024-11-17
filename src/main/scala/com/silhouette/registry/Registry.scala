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

class Registry[T] {
    var items: Map[String, T] = Map.empty[String, T]

    def registeritems(item: T): Unit =
        items(getUUID()) = item
    
    def assignIds(itemList: List[T]): List[(String, T)] =
        itemList.map(p => (getUUID() -> p))
    
    def registerProjects(itemList: List[T]): Unit =
        items.addAll(assignIds(itemList))
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
