package dk.dtu.cdiofinal.client.layout.recipe;

import java.util.ArrayList;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.CellTable;
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
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.cdiofinal.client.layout.AbstractView;
import dk.dtu.cdiofinal.client.layout.menu.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.ingredient.ClientIngredientImpl;
import dk.dtu.cdiofinal.client.serverconnection.recipe.ClientRecipeImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.IngredientDTO;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public class CreateEkstraRecipeCompView extends AbstractView {

	private ProdView prod;
	private ClientRecipeImpl serviceImpl;
	private ListDataProvider<RecipeComponentDTO> dataProvider;
	private static CreateEkstraRecipeCompUiBinder uiBinder = GWT.create(CreateEkstraRecipeCompUiBinder.class);
	private RecipeDTO recipe;
	private IngredientDTO ingredient = null;
	

	@UiTemplate("createEkstraRecipeComp.ui.xml")
	interface CreateEkstraRecipeCompUiBinder extends UiBinder<Widget, CreateEkstraRecipeCompView>{

	}

	//Textbox, buttons, heading and modal
	@UiField TextBox txt_Id;
	@UiField TextBox txt_net;
	@UiField TextBox txt_tolerance;
	@UiField Button btn_save_comp;
	@UiField Heading head_TitleId;
	@UiField Heading head_Id;
	@UiField Heading head_Nom_Net;
	@UiField Heading head_Tolerance;
	@UiField CellTable<RecipeComponentDTO> cellTable;
	@UiField Modal popup;
	@UiField Button btn_ok;
	@UiField Heading head_ok;

	public CreateEkstraRecipeCompView(ProdView prod, RecipeDTO recipe){
		initWidget(uiBinder.createAndBindUi(this));
		this.prod=prod;
		this.recipe=recipe;
		this.serviceImpl = new ClientRecipeImpl();
		this.dataProvider = new ListDataProvider<RecipeComponentDTO>();
		//Set visible and text on boxes and buttons
		head_TitleId.setText(recipe.getName());
		head_Id.setText("Ingredient ID");
		head_Nom_Net.setText("Nom_net");
		head_Tolerance.setText("Tolerance");
		//Add click and key handler to buttons and last textbox
		btn_ok.addClickHandler((ClickHandler)new OkClickHandler());
		btn_save_comp.addClickHandler(new SaveClickHandler());
		txt_tolerance.addKeyDownHandler((KeyDownHandler)new EnterHandler());
		//display celltable
		dataProvider.addDataDisplay(cellTable);

		//First column with Ingredient ID
		TextColumn<RecipeComponentDTO> IDColumn = new TextColumn<RecipeComponentDTO>(){
			@Override
			public String getValue(RecipeComponentDTO object) {
				return String.valueOf(object.getIngredient_ID());
			}
		};
		cellTable.addColumn(IDColumn, "Ingredient ID");

		//Column with net
		TextColumn<RecipeComponentDTO> netColumn = new TextColumn<RecipeComponentDTO>(){
			@Override
			public String getValue(RecipeComponentDTO object) {
				return String.valueOf(object.getNom_netto());
			}
		};
		cellTable.addColumn(netColumn, "Net");
		
		//Column with net
		TextColumn<RecipeComponentDTO> tolColumn = new TextColumn<RecipeComponentDTO>(){
			@Override
			public String getValue(RecipeComponentDTO object) {
				return String.valueOf(object.getTolerance());
			}
		};
		cellTable.addColumn(tolColumn, "Tolerance");

	}
	
	//checks if the information is valid, return false if true
	private boolean changeSucces(){
		String alert = "";
		boolean succes = true;
		if(!FieldVerifier.numberValid(Integer.parseInt(txt_Id.getText()))){
			alert+="Error - You need to write a valid batch ID \n";
			succes = false;
		}
		if(ingredient == null){
			alert+="Error - The ingredient does not exist \n";
			succes = false;
		}
		else if(!ingredient.isActive()){
			alert+="Error - The ingredient is not active \n";
			succes = false;
		}
		if(!FieldVerifier.netValid(Double.parseDouble(txt_net.getText()))){
			alert += "Error - net is not okay \n";
			succes = false;
		}
		if(!FieldVerifier.toleranceValid(Double.parseDouble(txt_tolerance.getText()))){
			alert += "Error - tolerance is not okay \n";
			succes = false;
		}
		if(!alert.equals(""))
			Window.alert(alert);
		return succes;
	}
	private void saveChanges(){
		// Checks to see if there is no errors
		if(changeSucces()){
		head_ok.setText("Your information has been saved");
			RecipeComponentDTO comp = new RecipeComponentDTO(recipe.getID(), Integer.parseInt(txt_Id.getText()), 
					"", Double.parseDouble(txt_tolerance.getText()), Double.parseDouble(txt_net.getText())); 
			//Updates the recipe with the new component
			recipe.addComponent(comp);
			ArrayList<RecipeComponentDTO> componentList = recipe.getComponents();
			//Show list of components
			dataProvider.setList(componentList);
			txt_Id.setText("");
			txt_net.setText("");
			txt_tolerance.setText("");
			//create recipecomponent in DB
			serviceImpl.createRecipeComponent(comp, new CreateRecipeCompponentCallback());
		}
		else{
			//if errors allow user to add a new component
			txt_Id.setText("");
			txt_net.setText("");
			txt_tolerance.setText("");
			txt_Id.setVisible(true);
			txt_net.setVisible(true);
			txt_tolerance.setVisible(true);
			btn_save_comp.setVisible(true);
			head_Id.setVisible(true);
			head_Nom_Net.setVisible(true);
			head_Tolerance.setVisible(true);
		}
	}
	
	private class OkClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.toggle();
			prod.PreviousView();
		}
	}
	
	
	private void whenSaveComponentIsClicked(){
		//set visibility on textboxes
		txt_Id.setVisible(false);
		txt_net.setVisible(false);
		txt_tolerance.setVisible(false);
		btn_save_comp.setVisible(false);
		head_Id.setVisible(false);
		head_Nom_Net.setVisible(false);
		head_Tolerance.setVisible(false);
		//checks the ingedient ID
		ingredientCheck(Integer.parseInt(txt_Id.getText()));
	}
	
	//find the ingredient with written ID
		private void ingredientCheck(int ID){
			ClientIngredientImpl serviceIngredientImpl = new ClientIngredientImpl();
			serviceIngredientImpl.getIngredient(ID, new DTOCallBack());
		}
	
	private class SaveClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			whenSaveComponentIsClicked();
		}
	}

	//Gets the ingredient with the written ID
		private class DTOCallBack implements AsyncCallback<IngredientDTO>{

			@Override
			public void onFailure(Throwable caught) {
				popup.setTitle("Error");
				head_ok.setText("No connection to server");
				popup.toggle();
			}

			@Override
			public void onSuccess(IngredientDTO result) {
				ingredient = result;
				//call saveChanges when DTO has been returned
				saveChanges();
			}
		}

	private class EnterHandler implements KeyDownHandler {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
				whenSaveComponentIsClicked();
			}		
		}	
	}
	
	//callback for create recipecomponent
	private class CreateRecipeCompponentCallback implements AsyncCallback<Boolean>{

		@Override
		public void onFailure(Throwable caught) {
			popup.setTitle("Error");
			head_ok.setText("No connection to server");
			popup.toggle();
		}
		@Override
		public void onSuccess(Boolean result) {
			if(result){
				popup.toggle();
				txt_Id.setText("");
			}
			else{
				popup.setTitle("Error");
				head_ok.setText("An error has occurred, and your information has not been saved.");
				popup.toggle();
			}
		}		
	}
		
	@Override
	public void Update() {
	}
}
