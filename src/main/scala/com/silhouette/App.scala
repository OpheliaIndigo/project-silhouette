package com.silhouette

import com.silhouette.project.Project
import com.silhouette.project.BasicProjectTemplate
import com.silhouette.project.ProjectLocalPath
import java.nio.file.Path

/** @author
  *   ${user.name}
  */

def foo(x: Array[String]) = x.foldLeft("")((a, b) => a + b)

@main def potato() =
    
    val projectBasePath = 
      os.Path("C:/Users/ophel/Projects/project-silhouette/template")

    val projectProjPath = ProjectLocalPath(
      projectBasePath / "testTemplate" / "proj"
    )
    val projectNotesPath = ProjectLocalPath(
      projectBasePath / "testTemplate" / "notes"
    )
    val projectOutPath = ProjectLocalPath(
      projectBasePath / "out"
    )

    val testProjTempl = BasicProjectTemplate(
      "Test Template",
      "Ophelia",
      List(
        projectProjPath
        // projectNotesPath
      )
    )
    Project("Potato", "Ophelia", testProjTempl, java.util.UUID.randomUUID.toString).init(projectOutPath, true)
