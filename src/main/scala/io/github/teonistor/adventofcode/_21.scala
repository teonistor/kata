package io.github.teonistor.adventofcode

object _21 {

  private val recipe = "([a-zA-Z ]+) \\(contains ([a-zA-Z ,]+)\\)".r

  private type Recipe = (Set[String], Set[String])

  private def parseRecipe(s: String): Recipe =
    s match {
      case recipe(ingredients, allergens) => (ingredients.split(' ').to(Set), allergens.split("[ ,]+").to(Set))
    }

  private def solveAllergens(input: String) = {
    val recipes = input.split('\n').to(LazyList).map(parseRecipe)

    /*  Doing some analysis with this...

    println(s"Recipe count: ${recipes.size}. Ingredient count: "
      + recipes.flatMap(_._1).distinct.size
      + ". Allergen count: "
      + recipes.flatMap(_._2).distinct.size)

    Recipe count: 4. Ingredient count: 7. Allergen count: 3
    Recipe count: 40. Ingredient count: 200. Allergen count: 8
*/
    val allergens = recipes.flatMap(_._2).to(Set)
    val possibleCulprits = allergens.map(allergen => (allergen, recipes.filter(_._2.contains(allergen)).map(_._1).reduce(_ & _))).toMap

    (recipes, recursively(possibleCulprits))
  }

  def recursively(culprits: Map[String, Set[String]]): Map[String, Set[String]] = {
    val solved = culprits.filter(_._2.size == 1).flatMap(_._2)

    if (solved.size == culprits.size)
      culprits
    else
      recursively(culprits.view.mapValues(culprit => if (culprit.size == 1) culprit else culprit.removedAll(solved)).toMap)
  }

  def _1(input: String): Int = {
    val (recipes, culprits) = solveAllergens(input)
    val definiteCulprits = culprits.flatMap(_._2).to(Set)

    recipes.map(_._1.removedAll(definiteCulprits).size).sum
  }

  def _2(input: String): String = {
    val (_, culprits) = solveAllergens(input)
    culprits.to(LazyList).sortBy(_._1).map(_._2.head).mkString(",")
  }
}
