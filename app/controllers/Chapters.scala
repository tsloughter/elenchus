package controllers

import models.Book
import models.Chapter
import models.User

import play.api._
import play.api.mvc._

object Chapters extends Controller {

  def show(id: Long, chapter: Int) = Action {
    val Some(b) = Book.findById(id)
    val chapters = Chapter.findByBook(id)
    val Some(c) = Chapter.findByBookIdAndPosition(id, chapter)
    val Some(u) = User.findById(b.user_id)
    
    val owner = u.username
    val book_title = b.title
    
    val chapter_content = scala.io.Source.fromFile("/opt/"+owner+"/"+id+"/"+chapter+"/chapter.html").mkString

    Ok(views.html.Chapters.show(chapters, book_title, chapter_content))
  }

}

  
