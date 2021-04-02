package com.example.helloworld.impl

import com.lightbend.lagom.scaladsl.server.LocalServiceLocator
import com.lightbend.lagom.scaladsl.testkit.ServiceTest
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import com.example.helloworld.api._
import com.lightbend.lagom.scaladsl.cluster.ClusterComponents
import com.lightbend.lagom.scaladsl.playjson.EmptyJsonSerializerRegistry
import com.lightbend.lagom.scaladsl.playjson.JsonSerializerRegistry

class HelloWorldServiceSpec extends AsyncWordSpec with Matchers with BeforeAndAfterAll {

  private val server = ServiceTest.startServer(
    ServiceTest.defaultSetup
  ) { ctx =>
    new HelloWorldApplication(ctx) with LocalServiceLocator
  }

  override protected def afterAll(): Unit = server.stop()

  "No clusters service" should {

    "use 'local' provider" in {
      val provider = server.actorSystem.settings.config.getString("akka.actor.provider")
      provider shouldBe "local"
    }
  }
}
