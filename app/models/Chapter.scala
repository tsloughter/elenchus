package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Chapter(id: Long, book_id: Long, position: Int, title: String)

object Chapter {
  val simple = {
    get[Long]("id") ~
    get[Long]("book_id") ~
    get[Int]("position") ~
    get[String]("title") map {
      case id~book_id~position~title => Chapter(id, book_id, position, title)
    }
  }

  def findByBook(book_id: Long): List[Chapter] = {
    DB.withConnection { implicit connection =>
      SQL("select * from chapter where book_id = {book_id}").on('book_id -> book_id).as(Chapter.simple *)
    }                  
  }

  def findByBookIdAndPosition(book_id: Long, position: Int): Option[Chapter] = {
    DB.withConnection { implicit connection =>
      SQL("select * from chapter where book_id = {book_id} and position = {position}").on('book_id -> book_id, 'position -> position).as(Chapter.simple.singleOpt)
    }                  
  }
  def all(): List[Chapter] = Nil   
  
  def create(book_id: Long, position: Int, title: String) {
    DB.withConnection { implicit connection =>
      SQL("insert into chapter(book_id, position, title) values ({book_id}, {position}, {title})").on(
        'book_id -> book_id,
        'position -> position,
        'title -> title
      ).executeUpdate()
    }
  }

  def delete(id: Long) {}
}
