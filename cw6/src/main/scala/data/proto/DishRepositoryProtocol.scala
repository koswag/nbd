package pl.edu.pja.s15165.cw6
package data.proto

import data.model.Dish


object DishRepositoryProtocol {
    case class StoreDish(dish: Dish)
    case class FetchDishByName(name: String)
}
