package org.juanitodread.pitayafinch.routes

import io.finch.catsEffect._

import org.juanitodread.pitayafinch.utils.AppConf

trait BaseEndpoint extends AppConf {
  final protected val basePath = serverCtx :: api :: version
}
