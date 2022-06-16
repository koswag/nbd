package pl.edu.pja.s15165.cw6
package data.model

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat


case class Ingredient(
    name: String,
    quantity: Int
)

object Ingredient {
    implicit val jsonFormat: RootJsonFormat[Ingredient] = jsonFormat2(Ingredient.apply)
}
