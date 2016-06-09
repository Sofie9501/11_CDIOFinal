package test;

import org.junit.Test;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.MySQL.IngredientsDAOMySQL;
import dk.dtu.cdiofinal.server.DAL.MySQL.RecipeCompDAOMySQL;
import dk.dtu.cdiofinal.server.DAL.MySQL.RecipeDAOMySQL;
import dk.dtu.cdiofinal.shared.IngredientDTO;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public class RecipeCompDBTest {

	@Test
	public void test() {
		RecipeDTO recipe = new RecipeDTO(99,"Test", true);
		IngredientDTO ingredient = new IngredientDTO(999, "Salt", "saltland", true);
		RecipeComponentDTO comp = new RecipeComponentDTO(99, 999, "Salt", 1.2, 5);
		RecipeComponentDTO testComp = null;

		IngredientsDAOMySQL ingrdientDAO = new IngredientsDAOMySQL();
		RecipeDAOMySQL recipeDAO = new RecipeDAOMySQL();
		RecipeCompDAOMySQL compDAO = new RecipeCompDAOMySQL();

		try {
			ingrdientDAO.createIngredient(ingredient);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		recipe.addComponent(comp);
		try {
			recipeDAO.createRecipe(recipe, recipe.getComponents());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			testComp = compDAO.getRecipeComp(99, 999);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(testComp==null){
			System.out.println("Error");
		}
		else{
			System.out.println("Det virker");
			System.out.println(testComp.toString());
		}
	}

}
