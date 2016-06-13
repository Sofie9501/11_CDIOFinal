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

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.layout.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.ingredient.ClientIngredientImpl;
import dk.dtu.cdiofinal.client.serverconnection.recipe.ClientRecipeImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.IngredientDTO;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public class CreateEkstraRecipeComp extends AbstractView {

	final ProdView prod;
	protected ClientRecipeImpl serviceImpl;
	private ListDataProvider<RecipeComponentDTO> dataProvider;

	private static CreateEkstraRecipeCompUiBinder uiBinder = GWT.create(CreateEkstraRecipeCompUiBinder.class);
	private RecipeDTO recipe;
	private IngredientDTO ingredient = null;
	

	@UiTemplate("createEkstraRecipeComp.ui.xml")
	interface CreateEkstraRecipeCompUiBinder extends UiBinder<Widget, CreateEkstraRecipeComp>{

	}

	//Textbox, buttons, heading and modal
	@UiField TextBox txt_ID;
	@UiField TextBox txt_net;
	@UiField TextBox txt_tolerance;
	@UiField Button btn_save_comp;
	@UiField Heading TitleID;
	@UiField Heading ID;
	@UiField Heading Nom_net;
	@UiField Heading Tolerance;
	@UiField CellTable<RecipeComponentDTO> cellTable;
	@UiField Modal popup;
	@UiField Button btn_ok;
	@UiField Heading ok;

	public CreateEkstraRecipeComp(ProdView prod, RecipeDTO recipe){
		initWidget(uiBinder.createAndBindUi(this));
		this.prod=prod;
		this.recipe=recipe;
		this.serviceImpl = new ClientRecipeImpl();
		this.dataProvider = new ListDataProvider<RecipeComponentDTO>();
		//Set visible and text on boxes and buttons
		TitleID.setText(recipe.getName());
		ID.setText("Ingredient ID");
		Nom_net.setText("Nom_net");
		Tolerance.setText("Tolerance");
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
	private boolean changeSucces(){
		String alert = "";
		boolean succes = true;
		if(!FieldVerifier.numberValid(Integer.parseInt(txt_ID.getText()))){
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
		if(!FieldVerifier.amountValid(Double.parseDouble(txt_net.getText()))){
			alert += "Error - net is not okay \n";
			succes = false;
		}
		if(!FieldVerifier.amountValid(Double.parseDouble(txt_tolerance.getText()))){
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
		ok.setText("Your information has been saved");
			RecipeComponentDTO comp = new RecipeComponentDTO(recipe.getID(), Integer.parseInt(txt_ID.getText()), 
					"", Double.parseDouble(txt_tolerance.getText()), Double.parseDouble(txt_net.getText())); 
			//Updates the recipe with the new component
			recipe.addComponent(comp);
			ArrayList<RecipeComponentDTO> componentList = recipe.getComponents();
			//Show list of components
			dataProvider.setList(componentList);
			txt_ID.setText("");
			txt_net.setText("");
			txt_tolerance.setText("");
			serviceImpl.createRecipeComponent(comp, new MyCallback());
		}
		else{
			txt_ID.setText("");
			txt_net.setText("");
			txt_tolerance.setText("");
			txt_ID.setVisible(true);
			txt_net.setVisible(true);
			txt_tolerance.setVisible(true);
			btn_save_comp.setVisible(true);
			ID.setVisible(true);
			Nom_net.setVisible(true);
			Tolerance.setVisible(true);
		}
	}
	
	private class OkClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			popup.toggle();
			prod.PreviousView();
		}
	}
	
	//
	private void whenSaveComponentIsClicked(){
		//set visibility on textboxes
		txt_ID.setVisible(false);
		txt_net.setVisible(false);
		txt_tolerance.setVisible(false);
		btn_save_comp.setVisible(false);
		ID.setVisible(false);
		Nom_net.setVisible(false);
		Tolerance.setVisible(false);
		ingredientCheck(Integer.parseInt(txt_ID.getText()));
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
				ok.setText("No connection to server");
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
	private class MyCallback implements AsyncCallback<Boolean>{

		@Override
		public void onFailure(Throwable caught) {
			popup.setTitle("Error");
			ok.setText("No connection to server");
			popup.toggle();
		}
		@Override
		public void onSuccess(Boolean result) {
			if(result){
				popup.toggle();
				txt_ID.setText("");
			}
			else{
				popup.setTitle("Error");
				ok.setText("An error has occurred, and your information has not been saved.");
				popup.toggle();
			}
		}		
	}
	
		
	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

}
