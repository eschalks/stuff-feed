package controllers

import play.api._
import play.api.mvc._
import models.{Feeds, FeedPosts}


object Application extends Controller {

  def index = Action {
    val posts = FeedPosts.findAll()
    Ok(views.html.index(posts))
  }

  def feed(feedId: String) = Action {

    val feed = Feeds.get(feedId)
    if (feed.isDefined) {
      val posts = FeedPosts.findForFeed(feedId, 0, 10)

      Ok(views.html.feed(feed.get, posts))
    } else {
      Ok(views.html.createFeed(feedId))
    }
  }

  def browse = Action {
    val topFeeds = Feeds.findTopFeeds()
    Ok(views.html.browse(topFeeds))
  }

}