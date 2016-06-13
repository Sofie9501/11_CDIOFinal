package dk.dtu.cdiofinal.client.layout.ingredientbatch;

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
import dk.dtu.cdiofinal.client.layout.menu.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.ingredientbatch.ClientIngredientBatchImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

public class CreateCertainIngredientBatchView extends AbstractView {

	final ProdView prod;
	private ClientIngredientBatchImpl serviceImpl;
	private static createCertainIngredientbatchUiBinder uiBinder = GWT.create(createCertainIngredientbatchUiBinder.class);
	private IngredientBatchDTO batch;

	@UiTemplate("createCertainIngredientBatch.ui.xml")
	interface createCertainIngredientbatchUiBinder extends UiBinder<Widget, CreateCertainIngredientBatchView>{

	}

	//Textbox, buttons, heading and modal
	@UiField
	TextBox txt_B_ID;
	@UiField
	TextBox txt_I_ID;
	@UiField
	TextBox txt_amount;
	@UiField
	Button btn_save;

	@UiField
	Modal popup;
	@UiField
	Button btn_ok;
	@UiField
	Heading ok;



	public CreateCertainIngredientBatchView(ProdView prod, int ID){
		initWidget(uiBinder.createAndBindUi(this));
		this.prod=prod;
		this.serviceImpl = new ClientIngredientBatchImpl();
		txt_I_ID.setText(String.valueOf(ID));
		//Add click and key handler to buttons and last textbox
		btn_save.addClickHandler(new SaveClickHandler());
		btn_ok.addClickHandler((ClickHandler)new OkClickHandler());
		txt_amount.addKeyDownHandler((KeyDownHandler)new EnterHandler());

	}
	private boolean changeSucces(){
		String alert = "";
		boolean succes = true;
		if(!FieldVerifier.numberValid(Integer.parseInt(txt_B_ID.getText()))){
			alert+="Error - You need to write a valid batch ID \n";
			succes = false;
		}
		if(!FieldVerifier.numberValid(Integer.parseInt(txt_I_ID.getText()))){
			alert += "Error - ID for ingredient is not valid \n";
			succes = false;
		}
		if(!alert.equals(""))
			Window.alert(alert);
		return succes;
	}
	private void saveChanges(){
		// Checks to see if there is no errors
		if(changeSucces()){
			batch = new IngredientBatchDTO(Integer.parseInt(txt_B_ID.getText()), null, Integer.parseInt(txt_I_ID.getText())
					,Double.parseDouble(txt_amount.getText()), true, null);
			ok.setText("Your information has been saved");
			//Updates the DB with the new operator
			serviceImpl.createIngredientBatch(batch, new MyCallback());
			
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
			prod.PreviousView();
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
			popup.setTitle("Error");
			ok.setText("An error has occurred, and your information has not been saved.");
			popup.toggle();
		}
		@Override
		public void onSuccess(Boolean result) {
			if(result){
			popup.toggle();
			txt_B_ID.setText("");
			txt_I_ID.setText("");
			txt_amount.setText("");
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
