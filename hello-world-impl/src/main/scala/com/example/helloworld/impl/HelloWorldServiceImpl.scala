package com.example.helloworld.impl

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import akka.NotUsed
import com.example.helloworld.api.HelloWorldService
import com.lightbend.lagom.scaladsl.api.ServiceCall

/**
  * Implementation of the HelloWorldService.
  */
class HelloWorldServiceImpl(
)(implicit ec: ExecutionContext)
  extends HelloWorldService {

  override def hello(id: String): ServiceCall[NotUsed, String] = ServiceCall {
    _ =>
      Future.successful(id)
  }

}
