package pl.edu.pja.s15165.cw6
package data.repos

import data.db.Riak

import akka.actor.{Actor, ActorLogging, ActorRef}
import data.model.Dish


class DishRepository extends Actor with ActorLogging {
    import data.proto.DishRepositoryProtocol._
    import context.dispatcher

    private val dishes = Riak.client.bucket("dishes")

    override def receive: Receive = {
        case StoreDish(dish) => storeDish(dish, sender)
        case FetchDishByName(name) => fetchDishByName(name, sender)
    }

    private def storeDish(dish: Dish, actor: ActorRef): Unit =
        dishes.storeAndFetch(dish.name, dish)
            .map(_.as[Dish])
            .onSuccess { case storedDish => actor ! storedDish }

    private def fetchDishByName(name: String, actor: ActorRef): Unit =
        dishes.fetch(name)
            .map { rawOption => rawOption.map(_.as[Dish]) }
            .onSuccess { case dishOption => actor ! dishOption }

}

object DishRepository {
    object RequestProtocol {
        case class StoreDish(dish: Dish)
        case class FetchDishByKey(key: String)
        case class DeleteDishByKey(key: String)
    }

    object ResponseProtocol {
        case class DishStored(dish: Dish)
        case class DishFetched(dishOption: Option[Dish])
        case class DishDeleted(key: String)
    }
}
