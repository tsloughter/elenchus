package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

class TestSpec extends Specification {

  import models._
  
  "respond to the chapter show Action" in {
    val result = controllers.Chapters.show(1, 1)(FakeRequest())

    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
    contentAsString(result) must contain("Chapter")
  }

  "Book model" should {

    "be retrieved by id" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {

        val Some(b) = Book.findById(1)

        b.title must equalTo("Real World Haskell")

      }
        }}
}
