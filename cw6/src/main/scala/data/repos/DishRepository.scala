package pl.edu.pja.s15165.cw6
package data.repos

import data.db.Riak
import data.model.Dish
import data.repos.DishRepository.RequestProtocol._
import data.repos.DishRepository.ResponseProtocol.{DishDeleted, DishFetched, DishStored}

import akka.actor.{Actor, ActorLogging, ActorRef}


class DishRepository extends Actor with ActorLogging {
    import context.dispatcher

    private val dishes = Riak.client(context.system).bucket("dishes")

    override def receive: Receive = {
        case StoreDish(dish) => storeDish(dish, context.sender)
        case FetchDishByKey(name) => fetchDishByID(name, context.sender)
        case DeleteDishByKey(key) => removeDishByID(key, context.sender)
        case other => log.error(s"Invalid request: $other")
    }

    private def storeDish(dish: Dish, actor: ActorRef): Unit =
        dishes.storeAndFetch(dish.key, dish)
            .map(_.as[Dish])
            .onSuccess {
                case storedDish => actor ! DishStored(storedDish)
            }

    private def fetchDishByID(key: String, actor: ActorRef): Unit =
        dishes.fetch(key)
            .map { rawOption => rawOption.map(_.as[Dish]) }
            .onSuccess {
                case dishOption => actor ! DishFetched(dishOption)
            }

    private def removeDishByID(key: String, actor: ActorRef): Unit =
        dishes.delete(key)
            .onSuccess {
                case () => actor ! DishDeleted(key)
            }

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
