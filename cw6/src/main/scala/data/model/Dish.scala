package pl.edu.pja.s15165.cw6
package data.model

case class Dish(
    name: String,
    description: String,
    price: Int,
    ingredients: List[Ingredient]
)
