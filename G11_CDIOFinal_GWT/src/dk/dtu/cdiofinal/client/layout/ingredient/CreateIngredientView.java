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

import dk.dtu.cdiofinal.client.layout.operator.CreateOprView;
import dk.dtu.cdiofinal.client.layout.operator.CreateOprView.CreateOprViewUiBinder;
import dk.dtu.cdiofinal.client.layout.operator.CreateOprView.EnterHandler;
import dk.dtu.cdiofinal.client.layout.operator.CreateOprView.MyCallback;
import dk.dtu.cdiofinal.client.layout.operator.CreateOprView.OkClickHandler;
import dk.dtu.cdiofinal.client.layout.operator.CreateOprView.SaveClickHandler;
import dk.dtu.cdiofinal.client.serverconnection.ServiceClientImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.IngredientDTO;
import dk.dtu.cdiofinal.shared.OperatoerDTO;

public class CreateIngredientView {
	private static CreateOprViewUiBinder uiBinder = GWT.create(CreateOprViewUiBinder.class);

	@UiTemplate("CreateIngredientView.ui.xml")
	interface CreateOprViewUiBinder extends UiBinder<Widget, CreateOprView>{

	}
	IngredientDTO ingre;
	ServiceClientImpl serviceImpl;

	@UiField
	TextBox txt_ingredientname;
	@UiField
	TextBox txt_id;
	@UiField
	TextBox txt_supplorname;
	@UiField
	Button btn_save;

	@UiField
	Modal popup;
	@UiField
	Button btn_ok;
	@UiField
	Heading ok;



	public CreateIngredientView(){
		initWidget(uiBinder.createAndBindUi(this));
		this.serviceImpl = new ServiceClientImpl();
		//Clickhandler
		btn_save.addClickHandler(new SaveClickHandler());
		btn_ok.addClickHandler((ClickHandler)new OkClickHandler());
	}
	private boolean changeSucces(){
		String alert = "";
		boolean succes = true;
		if(!FieldVerifier.nameValid(txt_ingredientname.getText())){
//			txt_name.setErrorLabel(errorLabel);
			alert+="Fejl - Du skal skrive et navn \n";
			succes = false;
		}
		if(!FieldVerifier.numberValid(txt_id.getText())){
			alert += "Fejl - id er ikke korrekt \n";
			succes = false;
		}
		if(!FieldVerifier.nameValid(txt_supplorname.getText())){
//			txt_name.setErrorLabel(errorLabel);
			alert+="Fejl - Du skal skrive et navn \n";
			succes = false;
		}
		if(!alert.equals(""))
			Window.alert(alert);
		return succes;
	}
	private void saveChanges(){
		// Checks to see if there is no errors
		if(changeSucces()){
			ingre = new IngredientDTO(Integer.parseInt(txt_id.getText()), txt_ingredientname.getText(), txt_supplorname.getText(),true);
			ok.setText("Dine oplysninger er blevet gemt");
			//Updates the DB with the new operator
			serviceImpl.createIngredient(ingre, new MyCallback());
			
		}	

	}
	private class SaveClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			saveChanges();
		}	
	}	
	private class OkClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			popup.toggle();

		}
	}
	private class EnterHandler implements KeyDownHandler {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
				saveChanges();
			}		
		}	
	}
	private class MyCallback implements AsyncCallback<Boolean>{

		@Override
		public void onFailure(Throwable caught) {
			popup.setTitle("Fejl");
			ok.setText("Der er sket en fejl og resultatet er ikke blevet gemt");
			popup.toggle();
		}
		@Override
		public void onSuccess(Boolean result) {
			if(result){
			popup.toggle();
			txt_ingredientname.setText("");
			txt_id.setText("");
			txt_supplorname.setText("");
			}
			else{
				popup.setTitle("Fejl");
				ok.setText("Der er sket en fejl og resultatet er ikke blevet gemt");
				popup.toggle();
			}
		}		
	}
//	@Override
//	public void Update() {
//		// TODO Auto-generated method stub
//		
//	}
}
