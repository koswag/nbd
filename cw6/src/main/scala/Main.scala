package pl.edu.pja.s15165.cw6

import data.model.{Dish, Ingredient}
import data.repos.DishRepository
import data.repos.DishRepository.ResponseProtocol.DishStored
import services.DishService

import akka.actor.{ActorSystem, Props}


object Main {

    private val SERVICE_NAME = "dishes-service"

    val system = ActorSystem(SERVICE_NAME)
    val dishRepository = system.actorOf(Props[DishRepository])
    val dishService = new DishService(dishRepository)

    private val dish = Dish(
        key = "pepperoni",
        name = "Pizza Pepperoni",
        description = "Pizza with cheese and pepperoni",
        price = 25,
        ingredients = List(
            Ingredient("Tomato sauce", 1),
            Ingredient("cheese", 3),
            Ingredient("Pepperoni", 5)
        )
    )


    def main(args: Array[String]): Unit = {
        storeAndFetchDish(dish)
        updateAndFetchDish(dish)
        deleteAndFetchDish(dish.key)

        system.shutdown()
    }


    def storeAndFetchDish(dish: Dish): Unit = {
        println("Saving new dish..")
        dishService.storeDish(dish) match {
            case DishStored(dish) => println(s"Dish[${dish.key}] stored")
        }

        println("Reading new dish..")
        dishService.fetchDish(dish.key).dishOption match {
            case Some(dish) => println(s"Dish stored: $dish")
            case None => println(s"Dish[${dish.key}] not found")
        }
    }


    def updateAndFetchDish(dish: Dish): Unit = {
        val updatedDish = dish.copy(
            ingredients = dish.ingredients :+ Ingredient("Red pepper", 3)
        )

        println("Saving updated dish..")
        dishService.storeDish(updatedDish) match {
            case DishStored(dish) => println(s"Dish[${dish.key}] updated")
        }

        println("Reading updated dish..")
        dishService.fetchDish(dish.key).dishOption match {
            case Some(dish) => println(s"Updated dish: $dish")
            case None => println(s"Dish[${dish.key}] not found")
        }
    }


    def deleteAndFetchDish(key: String): Unit = {
        println("Deleting dish..")
        dishService.deleteDish(key)

        dishService.fetchDish(key).dishOption match {
            case Some(dish) => println(s"Dish is not deleted: $dish")
            case None => println(s"Dish[$key] deleted successfully")
        }
    }

}
