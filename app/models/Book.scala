package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
  
case class Book(id: Long, user_id: Long, title: String)

object Book {
  val simple = {
    get[Long]("id") ~
    get[Long]("user_id")~
    get[String]("title") map {
      case id~user_id~title => Book(id, user_id, title)
    }
  }

  def findById(id: Long): Option[Book] = {
    DB.withConnection { implicit connection =>
      SQL("select * from book where id = {id}").on('id -> id).as(Book.simple.singleOpt)
    }                  
  }
  
  def all(): List[Book] = {
    DB.withConnection { implicit connection =>
      SQL("select id, title from book").as(Book.simple *)
    }
  }
  
  def create(user_id: Long, title: String) {
    DB.withConnection { implicit connection =>
      SQL("insert into book(user_id, title) values ({user_id}, {title})").on(
        'user_id -> user_id,
        'title -> title
      ).executeUpdate()
    }
  }

  def delete(id: Long) {}
}
