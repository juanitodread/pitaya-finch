package org.juanitodread.pitayafinch

import org.juanitodread.pitayafinch.routes.EndpointsUtil
import org.juanitodread.pitayafinch.utils.AppConf

import com.twitter.finagle.Http
import com.twitter.util.Await

object App extends AppConf {

  def main(args: Array[String]): Unit = {
    val listen = ":" + port

    println(s"Server is starting at: ${listen}/${serverCtx}")
    Await.ready(Http.serve(listen, EndpointsUtil.toService))
  }
}