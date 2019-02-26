package main

import services.XMLTools

object Main {
  def main(args: Array[String]) {

    val service = new XMLTools()
    service.readXML("dataset.csv","TuXml.xml","Nombre del autor del podcast")
  }
}
