package dk.dtu.cdiofinal.client.layout.ingredient;

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

import dk.dtu.cdiofinal.client.layout.AbstractView;
import dk.dtu.cdiofinal.client.layout.menu.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.ingredient.ClientIngredientImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.IngredientDTO;

public class CreateIngredientView extends AbstractView{
	private static CreateIngredientViewUiBinder uiBinder = GWT.create(CreateIngredientViewUiBinder.class);
	private IngredientDTO ingredient;
	private ClientIngredientImpl serviceImpl;
	private ProdView prod;
	
	@UiTemplate("createIngredientView.ui.xml")
	interface CreateIngredientViewUiBinder extends UiBinder<Widget, CreateIngredientView>{
	}

	//textbox, button, modal and heading
	@UiField TextBox txt_name;
	@UiField TextBox txt_id;
	@UiField TextBox txt_supplier;
	@UiField Button btn_save;
	@UiField Modal popup;
	@UiField Button btn_ok;
	@UiField Heading head_ok;

	public CreateIngredientView(ProdView prod){
		initWidget(uiBinder.createAndBindUi(this));
		this.prod=prod;
		this.serviceImpl = new ClientIngredientImpl();
		//Clickhandler
		btn_save.addClickHandler(new SaveClickHandler());
		btn_ok.addClickHandler(new OkClickHandler());
		txt_supplier.addKeyDownHandler(new EnterHandler());		
	}
	
	//checks if the information is valid
	private boolean changeSucces(){
		String alert = "";
		boolean succes = true;
		if(!FieldVerifier.nameValid(txt_name.getText())){
			alert+="Error - You need to write a name \n";
			succes = false;
		}
		if(!FieldVerifier.numberValid(Integer.parseInt(txt_id.getText()))){
			alert += "Wrong - id is not valid \n";
			succes = false;
		}
		if(!FieldVerifier.nameValid(txt_supplier.getText())){
			alert+="Error - You need to write a name \n";
			succes = false;
		}
		if(!alert.equals(""))
			Window.alert(alert);
		return succes;
	}
	
	//save the ingredient
	private void saveChanges(){
		// Checks to see if there is no errors
		if(changeSucces()){
			ingredient = new IngredientDTO(Integer.parseInt(txt_id.getText()), txt_name.getText(), txt_supplier.getText(),true);
			head_ok.setText("Your information has been saved");
			//Updates the DB with the new ingredient
			serviceImpl.createIngredient(ingredient, new SaveIngredientCallback());
			
		}	

	}
	
	//click handler for save button
	private class SaveClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			saveChanges();
		}	
	}
	
	//click handler for when od is clicked
	private class OkClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			popup.toggle();
			prod.PreviousView();
		}
	}
	
	//makes it posible to hit enter to save
	private class EnterHandler implements KeyDownHandler {
		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
				saveChanges();
			}		
		}	
	}
	
	//callback for create ingredient
	private class SaveIngredientCallback implements AsyncCallback<Boolean>{
		@Override
		public void onFailure(Throwable caught) {
			popup.setTitle("Error");
			head_ok.setText("An error has occured, and the information has not been saved");
			popup.toggle();
		}
		@Override
		public void onSuccess(Boolean result) {
			if(result){
			popup.toggle();
			txt_name.setText("");
			txt_id.setText("");
			txt_supplier.setText("");
			}
			else{
				popup.setTitle("Error");
				head_ok.setText("An error has occured, and the information has not been saved");
				popup.toggle();
			}
		}		
	}
	@Override
	public void Update() {
	}
}