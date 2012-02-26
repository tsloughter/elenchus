package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
 
case class Comment(id: Long, body: String)

object Comment {
  def all(): List[Comment] = Nil

  def create(body: String) {}

  def delete(id: Long) {}
}
