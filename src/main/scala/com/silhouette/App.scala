package com.silhouette

/**
 * @author ${user.name}
 */
  
def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)

@main def potato() =
  println( "Hello World!" )
  println("concat arguments = " + foo(Array("potato")))

