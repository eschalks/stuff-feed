package models
import play.api.db.slick.Config.driver.simple._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger

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

    database.withSession { implicit s:Session =>
      Feeds.insert(newFeed)
    }
  }

  def findTopFeeds() = database.withSession { implicit s: Session =>
    val q = Query(Feeds)
            .map(feed => (feed, FeedPosts.where(_.feedId === feed.id).length))
            .sortBy(_._2.desc)

    Logger.info(q.selectStatement)
    q.list
  }

  def get(id: String) = {
    database.withSession { implicit s: Session =>
      val q = Query(Feeds)
                .filter(_.id === id)
      q.firstOption
    }
  }
}
