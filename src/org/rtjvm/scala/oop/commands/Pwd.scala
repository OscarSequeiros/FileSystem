package org.rtjvm.scala.oop.commands
import org.rtjvm.scala.oop.filesystem.State

class Pwd extends Command {

  def apply(state: State): State =
    state.setMessage(state.wd.path)
}
