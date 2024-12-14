package com.silhouette.project

import upickle.default._
import os.Path
import os.PermSet

implicit val pathReadWrite: ReadWriter[Path] =
    upickle.default.readwriter[String].bimap[Path](
        p => s"${p.toString()}",
        str => Path(str)
    )

sealed trait Readable

sealed trait ProjectPath {
    def createIfNotExists(): Unit
    def writeFile(filename: String, contents: String): Unit
    def readProject(): Option[Project]

}
case class ProjectLocalPath(path: os.Path) extends ProjectPath derives ReadWriter{
    override def createIfNotExists(): Unit =
        os.makeDir.all(path)
    
    override def writeFile(filename: String, fileContents: String): Unit = 
        os.write.over(path / filename, fileContents)

    override def readProject(): Option[Project] =
        if (os.list(path).find(p => p.getSegment(-1) == ".slh.json").isDefined) {
            return Some(read[Project](os.read(path / ".slh.json")))
        }
        return None
}

sealed trait ProjectTemplate derives ReadWriter{
    val name: String // Project Template Name
    val author: String // Project Template Author
    val dirs: List[ProjectLocalPath]

    def initProjectFolders(newDir: ProjectPath, replaceExisting: Boolean): Unit
}

case class BasicProjectTemplate(
    val name: String,
    val author: String,
    val dirs: List[ProjectLocalPath]

) extends ProjectTemplate{
    override def initProjectFolders(
        newDir: ProjectPath,
        replaceExisting: Boolean
    ): Unit = {
        newDir.createIfNotExists()
        newDir match
            case ProjectLocalPath(path) =>
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
