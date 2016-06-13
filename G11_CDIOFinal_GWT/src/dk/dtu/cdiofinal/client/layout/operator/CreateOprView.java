package dk.dtu.cdiofinal.client.layout.operator;

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
import dk.dtu.cdiofinal.client.serverconnection.operator.ClientOperatorImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.OperatorDTO;

public class CreateOprView extends AbstractView {

	private static CreateOprViewUiBinder uiBinder = GWT.create(CreateOprViewUiBinder.class);
	final ProdView prod;

	@UiTemplate("createOprView.ui.xml")
	interface CreateOprViewUiBinder extends UiBinder<Widget, CreateOprView>{

	}
	OperatorDTO opr;
	ClientOperatorImpl serviceImpl;

	@UiField
	TextBox txt_name;
	@UiField
	TextBox txt_cpr;
	@UiField
	TextBox txt_rolle;
	@UiField
	TextBox txt_ID;
	@UiField
	TextBox txt_password;
	@UiField
	Button btn_save;

	@UiField
	Modal popup;
	@UiField
	Button btn_ok;
	@UiField
	Heading ok;



	public CreateOprView(ProdView prod){
		initWidget(uiBinder.createAndBindUi(this));
		this.prod=prod;
		this.serviceImpl = new ClientOperatorImpl();
		//Clickhandler
		btn_save.addClickHandler(new SaveClickHandler());
		btn_ok.addClickHandler((ClickHandler)new OkClickHandler());
		txt_rolle.addKeyDownHandler((KeyDownHandler)new EnterHandler());

	}
	private boolean changeSucces(){
		String alert = "";
		boolean succes = true;
		if(!FieldVerifier.nameValid(txt_name.getText())){
			alert+="Error - You need to write a name \n";
			succes = false;
		}
		if(!FieldVerifier.numberValid(Integer.parseInt(txt_ID.getText()))){
			alert+="Error - Input for ID not valid \n";
			succes = false;
		}
		if(!FieldVerifier.cprValid(txt_cpr.getText())){
			alert += "Error - CPR number not correct \n";
			succes = false;
		}
		if(!FieldVerifier.passwordValid(txt_password.getText())){
			alert += "Error - Password does nor follow the rules \n";
			succes = false;
		}
		if(!FieldVerifier.roleValid(Integer.parseInt(txt_rolle.getText()))){
			alert += "Error - Role does not exist \n";
			succes = false;
		}
		if(!alert.equals(""))
			Window.alert(alert);
		return succes;
	}
	private void saveChanges(){
		// Checks to see if there is no errors
		if(changeSucces()){
			opr = new OperatorDTO(Integer.parseInt(txt_ID.getText()), txt_name.getText(), txt_cpr.getText(), txt_password.getText(), Integer.parseInt(txt_rolle.getText())
					, true);
			ok.setText("Your information has been saved");
			//Updates the DB with the new operator
			serviceImpl.createOperator(opr, new MyCallback());
			
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
			ok.setText("An error has occured, and the information has not been saved");
			popup.toggle();
		}
		@Override
		public void onSuccess(Boolean result) {
			if(result){
			popup.toggle();
			txt_name.setText("");
			txt_cpr.setText("");
			txt_rolle.setText("");
			txt_ID.setText("");
			txt_password.setText("");
			}
			else{
				popup.setTitle("Error");
				ok.setText("An error has occured, and the information has not been saved");
				popup.toggle();
			}
		}		
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
}
