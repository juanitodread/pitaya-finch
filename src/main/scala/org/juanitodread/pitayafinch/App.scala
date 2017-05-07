package org.juanitodread.pitayafinch

import com.twitter.finagle.Http
import com.twitter.util.Await

import io.finch._
import io.finch.circe._
import org.juanitodread.pitayafinch.routes.EndpointsUtil
import org.juanitodread.pitayafinch.utils.AppConf

object App extends AppConf {

  def main(args: Array[String]): Unit = {
    val listen = ":" + port

    val api0: Endpoint[String] = get("hello") { Ok("Hello, World!") }
    val api1: Endpoint[String] = get("hello1") { Ok("Hello, World!") }
    val api = api0 :+: api1

    println(s"Server is starting at: ${listen}/${serverCtx}")
    Await.ready(Http.serve(listen, EndpointsUtil.toService))
  }
}