package org.juanitodread.pitayafinch.utils

import com.typesafe.config.ConfigFactory

trait AppConf {
  private[this] val config = ConfigFactory.load()

  // server
  private[this] val server = config.getConfig("server")

  // api
  private[this] val apiCfg = config.getConfig("api")

  // public server conf
  val serverCtx = server.getString("context")
  val port = server.getString("port")

  // public api conf
  val serviceName = apiCfg.getString("service")
  val api = apiCfg.getString("context")
  val version = apiCfg.getString("version")
}