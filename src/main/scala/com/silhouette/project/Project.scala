package com.silhouette.project

import upickle.default._

case class Project(
    val name: String,
    val author: String,
    val template: ProjectTemplate
) derives ReadWriter {

    def init(location: ProjectPath, replaceExisting: Boolean = false) = {
        template.initProjectFolders(location, replaceExisting)
        this.buildProjectJson(location)
        
    }

    def buildProjectJson(projectLocation: ProjectPath) = 
        // Purpose of this is to dump PropjectDetails to a json file
        // The registry will pick this up and parse it just fine - this just needs to create the project details in the project folder
        // Registry stores id + projectConfigName, folderName, superDirectory
        // Project does NOT need to know where it is
        val jsonConfig: String = write(this)
        projectLocation.writeFile(".slh.json", jsonConfig)

}
