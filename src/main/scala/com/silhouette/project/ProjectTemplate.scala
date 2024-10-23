package com.silhouette.project

import upickle.default._
import os.Path

implicit val pathReadWrite: ReadWriter[Path] =
    upickle.default.readwriter[String].bimap[Path](
        p => s"${p.toString()}",
        str => Path(str)
    )

sealed trait ProjectTemplatePath {
    def createIfNotExists(): Unit
}
case class ProjectTemplateLocalPath(path: os.Path) extends ProjectTemplatePath derives ReadWriter{
    override def createIfNotExists(): Unit =
        os.makeDir.all(path)
}

sealed trait ProjectTemplate derives ReadWriter{
    val name: String // Project Template Name
    val author: String // Project Template Author
    val dirs: List[ProjectTemplateLocalPath]

    def initProjectFolders(newDir: ProjectTemplatePath, replaceExisting: Boolean): Unit
}

case class BasicProjectTemplate(
    val name: String,
    val author: String,
    val dirs: List[ProjectTemplateLocalPath]

) extends ProjectTemplate {
    override def initProjectFolders(
        newDir: ProjectTemplatePath,
        replaceExisting: Boolean
    ): Unit = {
        newDir.createIfNotExists()
        newDir match
            case ProjectTemplateLocalPath(path) =>
                dirs.map((dir) =>
                    os.copy.into(
                      dir.path,
                      path,
                      createFolders = true,
                      replaceExisting = replaceExisting,
                      mergeFolders = true
                    )
                )
    }
}
