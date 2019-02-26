package services
import java.io.{File, PrintWriter}

import scala.xml._
import scala.xml.transform.{RewriteRule, RuleTransformer}

class XMLTools {

  def readXML(fileNameCsv: String, fileNameXML:String, authorName:String): Unit ={
    var listaRules = List[RuleTransformer]()
    var seqRules = Seq[Node]()

    val xmlres = "xmlfile_out.xml"
    val bufferedSource = scala.io.Source.fromFile(fileNameCsv)
    val title= 0
    val urlPodcast = 2
    for (line <- bufferedSource.getLines.drop(0)) {
      val col = line.split(",").map(_.trim)
      val titulo_Episodio_Podcast: String = col(title)
      val urlEpisodioPodcast:String = col(urlPodcast)
      val addItemPodcast =
        <item>
          <title>{ titulo_Episodio_Podcast }</title>
          <link>{urlEpisodioPodcast}</link>
          <dc:creator>{authorName}</dc:creator>
          <category>Podcast</category>
          <pubDate>Thu, 10 Jan 2019 05:41:55 -0500</pubDate>
          <description>{ titulo_Episodio_Podcast }</description>
          <content:encoded>{s"<![CDATA[<h2>$titulo_Episodio_Podcast </h2><div></div>]]>"}</content:encoded>
          <enclosure url="" length="0" type="audio/mpeg" />
          <itunes:explicit>no</itunes:explicit>
          <itunes:author>{authorName}</itunes:author>
          <itunes:subtitle>{titulo_Episodio_Podcast}</itunes:subtitle>
          <itunes:summary>{titulo_Episodio_Podcast}</itunes:summary>
        </item>
      seqRules = seqRules++addItemPodcast
    }

    val  seqNode = addSeqNodeRule(seqRules)
    val  transformadaDeLaplace = new RuleTransformer(seqNode )
    val tes = XML.loadFile(fileNameXML)
    val resTransformada = transformadaDeLaplace(tes)
    scala.xml.XML.save(xmlres, resTransformada)

  }

  def addSeqNodeRule(addItems:Seq[Node]) = new RewriteRule {
    override def transform(n: Node): Seq[Node] = n match {
      case elem: Elem if elem.label == "channel" =>
        elem.copy(child = (elem.child ++ addItems))
      case n => n
    }
  }
}
