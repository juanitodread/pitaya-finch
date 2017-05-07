package org.juanitodread.pitayafinch.utils

import com.typesafe.config.ConfigFactory

trait AppConf {
  private[this] val config = ConfigFactory.load()

  // server
  private[this] val server = config.getConfig("server")

  // public spark conf
  val serverCtx = server.getString("context")
  val port = server.getString("port")
}