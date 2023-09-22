package utils

import spray.json._
import java.sql.Date
import java.text.SimpleDateFormat

object DateJsonProtocol extends DefaultJsonProtocol {
  implicit object DateJsonFormat extends JsonFormat[Date] {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

    def write(date: Date): JsValue = JsString(dateFormat.format(date))

    def read(json: JsValue): Date = json match {
      case JsString(s) => new Date(dateFormat.parse(s).getTime)
      case _           => deserializationError("Date expected")
    }
  }
}
