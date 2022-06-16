package pl.edu.pja.s15165.cw6
package services

import data.model.Dish
import data.repos.DishRepository.RequestProtocol.{DeleteDishByKey, FetchDishByKey, StoreDish}
import data.repos.DishRepository.ResponseProtocol.{DishDeleted, DishFetched, DishStored}

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Awaitable}
import scala.language.postfixOps


class DishService(
    private val dishRepository: ActorRef
) {

    private implicit val timeout = Timeout(5 seconds)

    def storeDish(dish: Dish): DishStored =
        request(dishRepository ? StoreDish(dish))
            .asInstanceOf[DishStored]

    def fetchDish(key: String): DishFetched =
        request(dishRepository ? FetchDishByKey(key))
            .asInstanceOf[DishFetched]

    def deleteDish(key: String): DishDeleted =
        request(dishRepository ? DeleteDishByKey(key))
            .asInstanceOf[DishDeleted]


    private def request(operation: Awaitable[_])(implicit timeout: Timeout): Any =
        Await.result(operation,
            atMost = timeout.duration)

}
