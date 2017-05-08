package org.juanitodread.pitayafinch.routes

import com.twitter.finagle.http.Request
import com.twitter.finagle.http.Response
import com.twitter.finagle.Service

import io.finch.circe._
import io.circe.generic.auto._

object EndpointsUtil {

  def toService: Service[Request, Response] = {
    (Index.index() :+:
      Index.help() :+:
      User.index() :+:
      User.help() :+:
      User.message()).toService
  }

}