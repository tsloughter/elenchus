package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class User(id: Long, username: String, email: String, password: String, fullname: String)

object User {
  val simple = {
    get[Long]("id") ~
    get[String]("username")~
    get[String]("email")~
    get[String]("password")~
    get[String]("fullname") map {
      case id~username~email~password~fullname => User(id, username, email, password, fullname)
    }
  }

  def findById(id: Long): Option[User] = {
    DB.withConnection { implicit connection =>
      SQL("select * from user where id = {id}").on('id -> id).as(User.simple.singleOpt)
    }
  }

  def all(): List[User] = {
    DB.withConnection { implicit connection =>
      SQL("select * from book").as(User.simple *)
    }
  }

  def create(username: String, email: String, password: String, fullname: String) {
    DB.withConnection { implicit connection =>
      SQL("insert into user(username, email, password, fullname) values ({username}, {email}, {password}, {fullname})").on(
        'username -> username, 'email -> email, 'password -> password, 'fullname -> fullname
      ).executeUpdate()
    }
  }

  def delete(id: Long) {}
}
