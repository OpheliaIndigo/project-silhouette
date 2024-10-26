package com.silhouette.project

import java.nio.file.Path
import upickle.default._

case class ProjectConfig(template: ProjectTemplate, path: String) derives ReadWriter

object ProjectConfig:
    def buildProjectJson() = 
        // Purpose of this is to dump PropjectDetails to a json file
        // The registry will pick this up and parse it just fine - this just needs to create the project details in the project folder
        // Registry stores id + projectConfigName, folderName, superDirectory
        // Project does NOT need to know where it is
        