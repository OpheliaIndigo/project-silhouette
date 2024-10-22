package com.silhouette

import com.silhouette.project.Project
import com.silhouette.project.BasicProjectTemplate
import com.silhouette.project.ProjectTemplateLocalPath

/** @author
  *   ${user.name}
  */

def foo(x: Array[String]) = x.foldLeft("")((a, b) => a + b)

@main def potato() =
    val projectBasePath = ProjectTemplateLocalPath(
      os.Path(
        "C:/Users/ophel/Projects/project-silhouette/template"
      )
    )

    val projectProjPath = ProjectTemplateLocalPath(
      projectBasePath.path / "testTemplate" / "proj"
    )
    val projectNotesPath = ProjectTemplateLocalPath(
      projectBasePath.path / "testTemplate" / "notes"
    )
    val projectOutPath = ProjectTemplateLocalPath(
      projectBasePath.path / "out"
    )

    val testProjTempl = BasicProjectTemplate(
      "Test Template",
      "Ophelia",
      List(
        projectProjPath,
        // projectNotesPath
      )
    )
    Project("Potato", "Ophelia", testProjTempl, projectOutPath).init(true)
