package org.rtjvm.scala.oop.files

import org.rtjvm.scala.oop.filesystem.FileSystemException

class File(override val name: String,
           override val parentPath: String,
           contents: String)
  extends DirEntry(parentPath, name) {

  override def asDirectory: Directory =
    throw new FileSystemException("A file cannot be converted to a directory")

  override def asFile: File = this

  override def getType: String = "File"
}

object File {

  def empty(parentPath: String, name: String): File =
    new File(parentPath, name, "")
}
