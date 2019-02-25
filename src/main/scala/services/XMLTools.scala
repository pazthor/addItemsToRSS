package services
import scala.xml.{XML}

class XMLTools {

  def readXML(fileName: String): Unit ={
    val xml = XML.loadString(xmlResponse)
    val current = xml \\ "current"
    val bufferedSource = scala.io.Source.fromFile(fileName)
    val title= 0
    val urlPodcast = 3
    for (line <- bufferedSource.getLines.drop(1)) {
      val col = line.split(",").map(_.trim)

      val tittlePodcast: String = col(title)
      val urlName:String = col(urlPodcast)
      val doc =
        <calendar>
          <week>
            <day>Monday</day>
            <day>Tuesday</day>
            <day>Wednesday</day>
            <day>Thursday</day>
            <day>Friday</day>
          </week>
          <year>
            <month>January</month>
            <month>February</month>
            <month>March</month>
          </year>
        </calendar>



    }
  }
  def addNode(to: Node, newNode: Node) = to match {
    case Elem(prefix, label, attributes, scope, child@_*) => Elem(prefix, label, attributes, scope, child ++ newNode: _*)
    case _ => println("could not find node"); to
  }

  def parseApiResponse(xmlResponse: String): WeatherResult = {

    val current = xml \\ "current"
    if (current.isEmpty)
      Error("error parsing xml response")
    else {
      val city = xml \\ "city" \ "@name"
      val temp = xml \\ "temperature" \ "@value"
      val min  = xml \\ "temperature" \ "@min"
      val max  = xml \\ "temperature" \ "@max"
      val weather = xml \\ "weather" \ "@value"
      val lastUpdate = xml \\ "lastupdate" \ "@value"
      WeatherReport(city.text, parseDouble(temp.text), parseDouble(min.text), parseDouble(max.text), weather.text, lastUpdate.text)
    }
  }
}
