package com.silhouette.project

case class Project(
    val name: String,
    val author: String,
    val template: ProjectTemplate,
    val location: ProjectTemplatePath
) {
    def init(replaceExisting: Boolean = false) = {
        template.initProjectFolders(location, replaceExisting)
        
    }
}
