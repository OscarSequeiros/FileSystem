package org.rtjvm.scala.oop.files

import org.rtjvm.scala.oop.filesystem.FileSystemException

class Directory(override val parentPath: String,
                override val name: String,
                val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {

  def hasEntry(name: String): Boolean = findEntry(name) != null

  def getAllFoldersInPath: List[String] =
    path.substring(1).split(Directory.SEPARATOR).toList.filter(!_.isEmpty)

  def findDescendant(path: List[String]): Directory =
    if (path.isEmpty) this
    else findEntry(path.head).asDirectory.findDescendant(path.tail)

  def addEntry(newEntry: DirEntry): Directory =
    new Directory(parentPath, name, contents :+ newEntry)

  def findEntry(entryName: String): DirEntry = {

    def findEntryHelper(entryName: String, contentList: List[DirEntry]): DirEntry =
      if (contentList.isEmpty) null
      else if (contentList.head.name.equals(entryName)) contentList.head
      else findEntryHelper(entryName, contentList.tail)

    findEntryHelper(entryName, contents)
  }

  def replaceEntry(entryName: String, newEntry: DirEntry): Directory =
    new Directory(parentPath, name, contents.filter(e => !e.name.equals(entryName)) :+ newEntry)

  override def asDirectory: Directory = this

  override def asFile: File = throw new FileSystemException("A directory cannot be converted to a File.")

  override def getType: String = "Directory"
}

object Directory {

  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory =  Directory.empty("", "")

  def empty(parentPath: String, name: String): Directory = new Directory(parentPath, name, List())
}
