package org.rtjvm.scala.oop.filesystem

import org.rtjvm.scala.oop.files.Directory

class State(val root: Directory, val wd: Directory, val output: String) {

  def show(): Unit = {
    println(output)
    print(State.SHELL_TOKEN)
  }

  def setMessage(message: String): State =
    State(root, wd, message)
}

object State {

  val SHELL_TOKEN = "$ "

  def apply(root: Directory, wd: Directory, output: String = "") =
    new State(root, wd, output)
}
