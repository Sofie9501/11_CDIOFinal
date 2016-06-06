package dk.dtu.cdiofinal.client.layout.operator;

import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.serverconnection.operator.ServiceClientOperatorImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.OperatoerDTO;

public class CreateOprView extends AbstractView {

	private static CreateOprViewUiBinder uiBinder = GWT.create(CreateOprViewUiBinder.class);

	@UiTemplate("createOprView.ui.xml")
	interface CreateOprViewUiBinder extends UiBinder<Widget, CreateOprView>{

	}
	OperatoerDTO opr;
	ServiceClientOperatorImpl serviceImpl;

	@UiField
	TextBox txt_name;
	@UiField
	TextBox txt_cpr;
	@UiField
	TextBox txt_rolle;
	@UiField
	Button btn_save;

	@UiField
	Modal popup;
	@UiField
	Button btn_ok;
	@UiField
	Heading ok;



	public CreateOprView(){
		initWidget(uiBinder.createAndBindUi(this));
		this.serviceImpl = new ServiceClientOperatorImpl();
		//Clickhandler
		btn_save.addClickHandler(new SaveClickHandler());
		btn_ok.addClickHandler((ClickHandler)new OkClickHandler());
		txt_rolle.addKeyDownHandler((KeyDownHandler)new EnterHandler());

	}
	private boolean changeSucces(){
		String alert = "";
		boolean succes = true;
		if(!FieldVerifier.nameValid(txt_name.getText())){
//			txt_name.setErrorLabel(errorLabel);
			alert+="Fejl - Du skal skrive et navn \n";
			succes = false;
		}
		if(!FieldVerifier.cprValid(txt_cpr.getText())){
			alert += "Fejl - Cprnummer er ikke korrekt \n";
			succes = false;
		}
		if(FieldVerifier.rolleValid(Integer.parseInt(txt_rolle.getText()))){
			alert += "Fejl - Rolle findes ikke \n";
			succes = false;
		}
		if(!alert.equals(""))
			Window.alert(alert);
		return succes;
	}
	private void saveChanges(){
		// Checks to see if there is no errors
		if(changeSucces()){
			opr = new OperatoerDTO(0, txt_name.getText(), Integer.parseInt(txt_rolle.getText())
					, txt_cpr.getText(), null);
			ok.setText("Dine oplysninger er blevet gemt");
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
			txt_name.setText("");
			txt_cpr.setText("");
			txt_rolle.setText("");
			}
			else{
				popup.setTitle("Fejl");
				ok.setText("Der er sket en fejl og resultatet er ikke blevet gemt");
				popup.toggle();
			}
		}		
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
}
