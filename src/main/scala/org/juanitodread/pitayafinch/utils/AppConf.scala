package org.juanitodread.pitayafinch.utils

import com.typesafe.config.{ Config, ConfigFactory }

trait AppConf {
  private[this] val config = ConfigFactory.load()

  // app
  private[this] val appCfg: Config = config.getConfig("app")

  // server
  private[this] val server: Config = config.getConfig("server")

  // api
  private[this] val apiCfg: Config = config.getConfig("api")

  // public app conf
  val lowResourcesMode = appCfg.getBoolean("lowResourcesMode")

  // public server conf
  val serverCtx: String = server.getString("context")
  val port: String = server.getString("port")

  // public api conf
  val serviceName = apiCfg.getString("service")
  val api = apiCfg.getString("context")
  val version = apiCfg.getString("version")
}