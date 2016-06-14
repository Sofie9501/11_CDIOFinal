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
import com.google.gwt.user.client.ui.Widget;

import dk.dtu.cdiofinal.client.layout.AbstractView;
import dk.dtu.cdiofinal.client.layout.menu.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.recipe.ClientRecipeImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public class CreateRecipeView extends AbstractView {

	private ProdView prod;
	private ClientRecipeImpl serviceImpl;
	private static createRecipeUiBinder uiBinder = GWT.create(createRecipeUiBinder.class);
	private RecipeDTO recipe;

	@UiTemplate("createRecipe.ui.xml")
	interface createRecipeUiBinder extends UiBinder<Widget, CreateRecipeView>{

	}

	//Textbox, buttons, heading and modal
	@UiField TextBox txt_Id;
	@UiField TextBox txt_name;
	@UiField Button btn_add;
	@UiField Modal popup;
	@UiField Button btn_ok;
	@UiField Heading head_ok;



	public CreateRecipeView(ProdView prod){
		initWidget(uiBinder.createAndBindUi(this));
		this.prod=prod;
		this.serviceImpl = new ClientRecipeImpl();
		//Add click and key handler to buttons and last textbox
		btn_ok.addClickHandler((ClickHandler)new OkClickHandler());
		btn_add.addClickHandler(new AddClickHandler());
		txt_name.addKeyDownHandler((KeyDownHandler)new EnterHandler());



	}
	//checks if the infromation is valid
	private boolean changeSucces(){
		String alert = "";
		boolean succes = true;
		if(!FieldVerifier.numberValid(Integer.parseInt(txt_Id.getText()))){
			alert+="Error - You need to write a valid ID \n";
			succes = false;
		}
		if(!FieldVerifier.nameValid(txt_name.getText())){
			alert += "Error - Name for Reipe is not valid \n";
			succes = false;
		}
		if(!alert.equals(""))
			Window.alert(alert);
		return succes;
	}
	private void saveRecipeDTO(){
		// Checks to see if there is no errors
		if(changeSucces()){
			recipe = new RecipeDTO(Integer.parseInt(txt_Id.getText()), txt_name.getText(), true);
			head_ok.setText("Your information has been saved");
			//goes to createCompview, 
			prod.setView(new CreateRecipeCompView(prod, recipe));
		}	

	}
	private class OkClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.toggle();
		}
	}


	private class AddClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			saveRecipeDTO();
		}
	}


	private class EnterHandler implements KeyDownHandler {
		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
				saveRecipeDTO();
			}		
		}	
	}
	@Override
	public void Update() {
	}
}
