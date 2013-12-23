package models
import play.api.db.slick.Config.driver.simple._
import play.api.Play.current
import play.api.db.DB

case class Feed(id: String, name: String)

object Feeds extends Table[Feed]("feed") {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def id = column[String]("id", O.PrimaryKey)
  def name = column[String]("name")

  def * = id ~ name <> (Feed, Feed.unapply _)

  def create(name: String) = {
    // Only allow alpha numeric characters and underscores for the id
    // Spaces will be replaced with underscores
    val id = name.toLowerCase.replace(" ", "_")
      .replaceAll("[^\\W]", "")

    val newFeed = Feed(id, name)

    database.withSession { implicit db:Session =>
      Feeds.insert(newFeed)
    }
  }

  def findAll = database.withSession {

  }
}
