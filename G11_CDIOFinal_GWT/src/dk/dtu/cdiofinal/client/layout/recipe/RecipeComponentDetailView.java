package dk.dtu.cdiofinal.client.layout.recipe;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.serverconnection.recipe.ClientRecipeImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;

public class RecipeComponentDetailView extends AbstractView{

	private static RecipeComponentDetailUiBinder uiBinder = GWT.create(RecipeComponentDetailUiBinder.class);


	@UiTemplate("recipeComponentDetail.ui.xml")
	interface RecipeComponentDetailUiBinder extends UiBinder<Widget, RecipeComponentDetailView>{

	}
	private RecipeComponentDTO comp;
	private ClientRecipeImpl serviceImpl;
	private int oldRecipeID;
	private int oldIngredientID;

	//Adds the main page texts
	@UiField Heading txt_recipeID; 
	@UiField Heading txt_ingredientID; 
	@UiField Heading txt_Nom; 
	@UiField Heading txt_tol; 	

	// Adds the edit buttons
	@UiField
	Button btn_recipeID;
	@UiField
	Button btn_ingredientID;
	@UiField
	Button btn_Nom;
	@UiField
	Button btn_tol;

	//Add editpopup with save button
	@UiField
	Modal popup;
	@UiField
	TextBox txt_edited;
	@UiField
	Button btn_save;

	//Constuctor
	public RecipeComponentDetailView(RecipeComponentDTO comp) {
		this.comp=comp;
		oldRecipeID=comp.getRecipe_ID();
		oldIngredientID=comp.getIngredient_ID();
		initWidget(uiBinder.createAndBindUi(this));
		this.serviceImpl = new ClientRecipeImpl();

		//Adds all the information on the operators
		txt_recipeID.setText(String.valueOf(comp.getRecipe_ID()));
		txt_ingredientID.setText(String.valueOf(comp.getIngredient_ID()));
		txt_Nom.setText(String.valueOf(comp.getNom_netto()));
		txt_tol.setText(String.valueOf(comp.getTolerance()));

		//Clickhandlers for all the buttons
		btn_recipeID.addClickHandler(new EditRecipeIDClickHandler());
		btn_ingredientID.addClickHandler(new EditIngredientIDClickHandler());
		btn_Nom.addClickHandler(new EditNetClickHandler());
		btn_tol.addClickHandler(new EditToleranceClickHandler());
		btn_save.addClickHandler(new SaveClickHandler());
		txt_edited.addKeyDownHandler((KeyDownHandler) new EnterHandler());
	}
	//Checkes the ID of the popup to see what kind of edit it is, validates input and saves it.
	private void saveChanges(){
		switch(popup.getId()){
		case "Recipe ID":
			if (!FieldVerifier.numberValid(Integer.parseInt(txt_edited.getText()))){
				Window.alert("Error - You need to write a valid ID");
			}
			else{
				comp.setRecipe_ID(Integer.parseInt(txt_edited.getText()));
				txt_recipeID.setText(String.valueOf(comp.getRecipe_ID()));
			}
			break;
		case "Ingredient ID":
			if (!FieldVerifier.numberValid(Integer.parseInt(txt_edited.getText()))){
				Window.alert("Error - Ingredient ID not valid");
			}
			else{
				comp.setIngredient_ID(Integer.parseInt(txt_edited.getText()));
				txt_ingredientID.setText(String.valueOf(comp.getIngredient_ID()));
			}
			break;
		case "Net":
			if(FieldVerifier.amountValid(Double.parseDouble(txt_edited.getText()))){
				comp.setNom_netto(Double.parseDouble(txt_edited.getText()));
				txt_Nom.setText(String.valueOf(comp.getNom_netto()));
			}
			else{
				Window.alert("Error - Wrong input");
			}
			break;
		case "tol":
			if(!FieldVerifier.amountValid(Double.parseDouble(txt_edited.getText()))){
				Window.alert("Error - Wrong input");
			}
			else{
				comp.setTolerance(Double.parseDouble(txt_edited.getText()));
				txt_tol.setText(String.valueOf(comp.getTolerance()));
			}
		}
		serviceImpl.updateRecipeComponent(comp, oldRecipeID, oldIngredientID, new MyCallback());
	}
	private class MyCallback implements AsyncCallback<Boolean>{

		@Override
		public void onFailure(Throwable caught) {
			popup.setTitle("Error");

		}
		@Override
		public void onSuccess(Boolean result) {
			if(result){
				popup.toggle();
			}
			else{
				popup.setTitle("Error");

			}
		}		
	}
	// Makes it posible to hit ENTER instead of the Save button.
	private class EnterHandler implements KeyDownHandler {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
				saveChanges();
			}		
		}	
	}
	//Clickhandlers for all the different buttons.
	private class EditRecipeIDClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change Recipe ID");
			popup.setId("Recipe ID");
			txt_edited.setText(String.valueOf(comp.getRecipe_ID()));
			popup.toggle();		
		}	
	}
	private class EditIngredientIDClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change Ingredient ID");
			popup.setId("Ingredient ID");
			txt_edited.setText(String.valueOf(comp.getIngredient_ID()));
			popup.toggle();		
		}	
	}
	private class EditNetClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change Nom Net");
			popup.setId("Net");
			txt_edited.setText(String.valueOf(comp.getNom_netto()));
			popup.toggle();		
		}	
	}
	private class EditToleranceClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change Tolerance");
			popup.setId("tol");
			txt_edited.setText(String.valueOf(comp.getTolerance()));
			popup.toggle();		
		}	
	}
	private class SaveClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			saveChanges();
		}	
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

}
