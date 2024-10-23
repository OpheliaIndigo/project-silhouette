package com.silhouette.project

import java.nio.file.Path
import upickle.default._

case class ProjectConfig(template: ProjectTemplate, path: String) derives ReadWriter

object ProjectConfig:
    def buildProjectJson() = 
        //