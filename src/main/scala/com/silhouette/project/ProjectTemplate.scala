package com.silhouette.project

import java.nio.file.Path

sealed trait ProjectTemplatePath
case class ProjectTemplateLocalPath(path: os.Path) extends ProjectTemplatePath

trait ProjectTemplate {
    val name: String // Project Template Name
    val author: String // Project Template Author

    def initProjectFolders(newDir: ProjectTemplatePath): Unit
}

class BasicProjectTemplate(
    val name: String,
    val author: String,
    val dirs: List[ProjectTemplateLocalPath]
) extends ProjectTemplate {
    override def initProjectFolders(
        newDir: ProjectTemplatePath
    ): Unit = {
        newDir match
            case ProjectTemplateLocalPath(path) =>
                dirs.map((dir) => os.copy.into(dir.path, path))
    }
}
