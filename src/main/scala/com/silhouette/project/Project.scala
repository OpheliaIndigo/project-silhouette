package com.silhouette.project

case class Project(
    val name: String,
    val author: String,
    val template: ProjectTemplate,
    val location: ProjectTemplatePath
) {
    def init() = {
        template.initProjectFolders(location)
        
    }
}
