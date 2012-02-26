package controllers

import models.Book
import models.Chapter
import models.User

import java.io._
  
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Books extends Controller {
  val bookForm: Form[Book] = Form(
    mapping(
      "title" -> text(minLength = 1)
    )
    {      
      title => Book(1, 1, title)
    }
    {
      book => Some(book.title)
    }
  )

  def form = Action {
    Ok(views.html.Books.form(bookForm))
  }

  def editForm = Action {
    Ok(views.html.Books.form(bookForm))
  }
  
  def update(id: Long) = Action {
    val Some(b) = Book.findById(id)
    val Some(u) = User.findById(b.user_id)
    
    val owner = u.username
    
    for (chapter <- new File("/opt/"+owner+"/"+id+"/").listFiles) {
      val chapter_title = scala.io.Source.fromFile(chapter+"/title").mkString
      val chapter_pos = (chapter.toString.split("/").reverse.head).toInt
      
      System.out.println("Chapter title: " + chapter_title)
      System.out.println("Chapter pos: " + chapter_pos)

      Chapter.create(id, chapter_pos, chapter_title)
    }

    Ok("updated")
  }

  def submit = Action { implicit request =>
    bookForm.bindFromRequest.fold(
      // Form has errors, redisplay it
      errors => BadRequest(views.html.Books.form(errors)),

      // We got a valid User value, display the summary
      book => Ok(views.html.Books.show(book.title))
    )                  
  }
  
  def show(id: Long) = Action {
    Ok(views.html.Books.show("Real World Haskell"))
  }
  
}
