package org.juanitodread.pitayafinch

import com.twitter.finagle.Http
import com.twitter.finagle.param.Stats
import com.twitter.server.TwitterServer
import com.twitter.util.Await

import org.juanitodread.pitayafinch.routes.Endpoints
import org.juanitodread.pitayafinch.utils.AppConf

object App extends TwitterServer with AppConf {

  def main(): Unit = {
    info(s"Service starting at http://<domain>:${port}/${serverCtx}")

    val server = Http.server
      .withLabel(serviceName)
      .configured(Stats(statsReceiver))
      .serve(s":${port}", Endpoints.toService)

    onExit(server.close())
    Await.ready(adminHttpServer)
  }
}