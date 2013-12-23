package models

import play.api.db.slick.Config.driver.simple._
import org.joda.time.DateTime
import com.github.tototoshi.slick.JodaSupport._

case class User(id: Option[Long], username: String, password: String, email: String, registeredAt: DateTime)

object Users extends Table[User]("user") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def username = column[String]("username")
  def password = column[String]("password", O.DBType("CHAR(60)"))
  def email = column[String]("email")
  def registeredAt = column[DateTime]("registered_at")

  def idxUsername = index("idx_username", username, unique = true)


  def * = id.? ~ username ~ password ~ email ~ registeredAt <> (User, User.unapply _)
}