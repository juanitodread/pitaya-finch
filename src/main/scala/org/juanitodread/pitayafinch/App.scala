package org.juanitodread.pitayafinch

import com.twitter.finagle.Http
import com.twitter.util.Await

import org.juanitodread.pitayafinch.routes.Endpoints
import org.juanitodread.pitayafinch.utils.AppConf

object App extends AppConf {

  def main(args: Array[String]): Unit = {
    println(s"Service starting at http://<domain>:${port}/${serverCtx}")
    Await.ready(Http.server.serve(s":${port}", Endpoints.toService))
  }
}