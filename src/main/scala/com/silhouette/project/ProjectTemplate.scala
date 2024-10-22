package com.silhouette.project

sealed trait ProjectTemplatePath {
    def createIfNotExists(): Unit
}
case class ProjectTemplateLocalPath(path: os.Path) extends ProjectTemplatePath {
    override def createIfNotExists(): Unit =
        os.makeDir.all(path)
}

trait ProjectTemplate {
    val name: String // Project Template Name
    val author: String // Project Template Author

    def initProjectFolders(newDir: ProjectTemplatePath, replaceExisting: Boolean): Unit
}

class BasicProjectTemplate(
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
