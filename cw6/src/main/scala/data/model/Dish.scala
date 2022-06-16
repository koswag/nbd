package pl.edu.pja.s15165.cw6
package data.model

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat


case class Dish(
    name: String,
    description: String,
    price: Int,
    ingredients: List[Ingredient]
)

object Dish {
    implicit val jsonFormat: RootJsonFormat[Dish] = jsonFormat4(Dish.apply)
}
