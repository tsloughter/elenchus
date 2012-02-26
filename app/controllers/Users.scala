package controllers

import models.User

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Users extends Controller {
  val userForm: Form[User] = Form(
    mapping(
      "username" -> text(minLength = 3),
      "email" -> email,
      "password" -> text,
      "fullname" -> text
    )
    {
      (username, email, password, fullname) => User(1, username, email, password, fullname)
    }
    {
      user => Some(user.username, user.email, user.password, user.fullname)
    }
  )

  def form = Action {
    Ok(views.html.Users.form(userForm))
  }

  def editForm(id: Long) = Action {
    Ok(views.html.Users.form(userForm))
  }

  def submit = Action { implicit request =>
    userForm.bindFromRequest.fold(
      errors => BadRequest(views.html.Users.form(errors)),
      user => Ok(views.html.Users.show(user))
    )
  }
}
