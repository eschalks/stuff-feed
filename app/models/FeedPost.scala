package models

import play.api.db.slick.Config.driver.simple._
import org.joda.time.DateTime
import com.github.tototoshi.slick.JodaSupport._

case class FeedPost(id: Option[Long], feedId: String, userId: Long, title: String, linkUrl: String, postedAt: DateTime)

object FeedPosts extends Table[FeedPost]("feed_post") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def feedId = column[String]("feed_id")
  def userId = column[Long]("user_id")
  def title = column[String]("title")
  def linkUrl = column[String]("link_url")
  def postedAt = column[DateTime]("posted_at")

  def fkFeed = foreignKey("fk_feed", feedId, Feeds)(_.id)
  def fkPoster = foreignKey("fk_user", userId, Users)(_.id)

  def * = id.? ~ feedId ~ userId ~ title ~ linkUrl ~ postedAt <> (FeedPost, FeedPost.unapply _)
}
