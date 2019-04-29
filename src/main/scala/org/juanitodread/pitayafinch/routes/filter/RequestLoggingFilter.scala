package org.juanitodread.pitayafinch.routes.filter

import com.twitter.finagle.filter.LogFormatter
import com.twitter.finagle.http.{ Request, Response, Status, filter }
import com.twitter.finagle.{ Service, SimpleFilter }
import com.twitter.util._
import org.slf4j.{ Logger, LoggerFactory }

abstract class RequestLoggingFilter[REQ <: Request](val log: Logger, val formatter: LogFormatter[REQ, Response])
  extends SimpleFilter[REQ, Response] {

  final def apply(request: REQ, service: Service[REQ, Response]): Future[Response] = {
    val elapsed = Stopwatch.start()

    service(request).respond {
      case Return(response) => logSuccess(elapsed(), request, response)
      case Throw(error) => logException(elapsed(), request, error)
    }
  }

  final def logSuccess(replyTime: Duration, request: REQ, response: Response): Unit = {
    log.info(formatter.format(request, response, replyTime))
  }

  final def logException(duration: Duration, request: REQ, error: Throwable): Unit = {
    log.error(formatter.format(request, Response(request.version, Status.InternalServerError), duration), error)
  }
}

class SimpleLogFormatter extends filter.LogFormatter {
  override def format(request: Request, response: Response, responseTime: Duration): String = {
    new StringBuilder().append("[")
      .append("remote-address=").append(request.remoteAddress.getHostAddress).append(", ")
      .append("uri=").append(request.uri).append(", ")
      .append("method=").append(request.method).append(", ")
      .append("version=").append(request.version).append(", ")
      .append("status-code=").append(response.statusCode).append(", ")
      .append("content-length=").append(response.length).append(", ")
      .append("response-time=").append(responseTime.inMilliseconds).append(" ms, ")
      .append("user-agent=").append(request.userAgent.getOrElse("unknown"))
      .append("]")
      .toString()
  }

  override def formatException(request: Request, throwable: Throwable, responseTime: Duration): String = {
    throw new UnsupportedOperationException("Log throwable as empty 500s instead")
  }
}

object RequestLoggingFilter
  extends RequestLoggingFilter[Request](LoggerFactory.getLogger("http-log-filter"), new SimpleLogFormatter)
