package org.juanitodread.pitayafinch.routes

import com.twitter.finagle.http.Request
import com.twitter.util.Promise

import org.juanitodread.pitayafinch.UnitSpec

class EndpointsTest extends UnitSpec {
  private val baseApi = "/pitaya-finch/api/v1"
  private val endpoints = List(
    s"$baseApi",
    s"$baseApi/help",
    s"$baseApi/users",
    s"$baseApi/users/help",
    s"$baseApi/users/cm",
    s"$baseApi/crud/users",
    s"$baseApi/nlp/tokenizer")

  "An Endpoints object" should "provide the Finch service definition" in {
    val service = Endpoints.toService()

    endpoints.foreach { endpointUrl =>
      service.apply(Request(endpointUrl)) shouldBe a[Promise[_]]
    }
  }
}
