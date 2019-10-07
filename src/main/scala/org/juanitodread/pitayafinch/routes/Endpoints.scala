package org.juanitodread.pitayafinch.routes

import com.twitter.finagle.Service
import com.twitter.finagle.http.{ Request, Response }
import com.twitter.util.logging.Logging
import io.circe.generic.auto._
import io.circe.{ Encoder, Json }
import io.finch._
import io.finch.circe.dropNullValues._

import org.juanitodread.pitayafinch.model.PitayaError
import org.juanitodread.pitayafinch.routes.cookbook.CookbookEndpoints
import org.juanitodread.pitayafinch.routes.nlp.NlpEndpoints

object Endpoints {
  import HandleErrorsAsJson._

  def toService(): Service[Request, Response] = Bootstrap
    .serve[Text.Plain](Index.index() :+: Index.help())
    .serve[Text.Plain](User.index() :+: User.help())
    .serve[Application.Json](User.message())
    .serve[Application.Json](CookbookEndpoints.build())
    .serve[Application.Json](NlpEndpoints.build())
    .toService
}

object HandleErrorsAsJson extends Logging {
  def errorToJson(error: Error): Json = error match {
    case Error.NotPresent(_) =>
      info(error)
      toJson("MissedJSONProperty", error.getMessage)
    case Error.NotParsed(_, _, _) =>
      info(error)
      toJson("NotParsedJSONProperty", error.getMessage)
    case Error.NotValid(_, _) =>
      info(error)
      toJson("InvalidJSONProperty", error.getMessage)
  }

  def pitayaErrorToJson(ex: PitayaError): Json = ex match {
    case _ =>
      info(ex)
      toJson(ex.title, ex.message)
  }

  private def toJson(error: String, message: String) = {
    Json.obj(
      "error" -> Json.fromString(error),
      "message" -> Json.fromString(message))
  }

  implicit val errorEncoder: Encoder[Exception] = Encoder.instance {
    case error: Error => errorToJson(error)
    case Errors(errors) => Json.arr(errors.toList.map(errorToJson): _*)
    case ex: PitayaError => pitayaErrorToJson(ex)
  }
}
