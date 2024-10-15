package com.silhouette.registry

import java.nio.file.Path

// Registry has a location (folder)
// Registry contains a list of projects and templates
// Registry can be loaded, to make projects and templates available

// Either create a registry class, or create a registry collector?

def collectRegistryProjectDetails(path: Path) = 
    // iterate over path finding .slh.json files
    // Recursively
    // Parse into ProjectDetails objects
    