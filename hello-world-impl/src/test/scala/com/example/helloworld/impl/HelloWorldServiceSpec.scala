package com.example.helloworld.impl

import com.lightbend.lagom.scaladsl.server.LocalServiceLocator
import com.lightbend.lagom.scaladsl.testkit.ServiceTest
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import com.example.helloworld.api._

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
