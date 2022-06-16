package pl.edu.pja.s15165.cw6
package data.model

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat


case class Dish(
    key: String,
    name: String,
    description: String,
    price: Int,
    ingredients: List[Ingredient]
)

object Dish {
    implicit val jsonFormat: RootJsonFormat[Dish] = jsonFormat5(Dish.apply)
}
