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

public class OprDetail extends AbstractView{
	
	private static OprDetailUiBinder uiBinder = GWT.create(OprDetailUiBinder.class);
	
	
	@UiTemplate("oprdetail.ui.xml")
	interface OprDetailUiBinder extends UiBinder<Widget, OprDetail>{

	}
	OperatoerDTO opr;
	ServiceClientOperatorImpl serviceImpl;

	//Adds the main page texts
	@UiField
	Heading txt_oprID; 
	@UiField
	Heading txt_name; 
	@UiField
	Heading txt_CPRnr; 
	@UiField
	Heading txt_role; 	
	@UiField
	Heading txt_password;

	// Adds the edit buttons
	@UiField
	Button btn_name;
	@UiField
	Button btn_CPR;
	@UiField
	Button btn_rolle;
	@UiField
	Button btn_password;

	//Add editpopup with save button
	@UiField
	Modal popup;
	@UiField
	TextBox txt_edited;
	@UiField
	Button btn_save;

	//Constuctor
	public OprDetail(OperatoerDTO opr) {
		this.opr=opr;
		initWidget(uiBinder.createAndBindUi(this));
		this.serviceImpl = new ServiceClientOperatorImpl();

		//Adds all the information on the operators
		txt_oprID.setText(String.valueOf(opr.getOprID()));
		txt_name.setText(opr.getOprNavn());
		
		
		txt_CPRnr.setText(FieldVerifier.cprFormat(opr.getCpr()));
		txt_role.setText(String.valueOf(opr.getRolle()));
		txt_password.setText(opr.getPassword());

		//Clickhandlers for all the buttons
		btn_name.addClickHandler((ClickHandler)new EditNameClickHandler());
		btn_CPR.addClickHandler((ClickHandler) new EditCprClickHandler());
		btn_rolle.addClickHandler((ClickHandler) new EditRoleClickHandler());
		btn_password.addClickHandler((ClickHandler) new EditPasswordClickHandler());
		btn_save.addClickHandler((ClickHandler) new SaveClickHandler());
		txt_edited.addKeyDownHandler((KeyDownHandler) new EnterHandler());
	}
	//Checkes the ID of the popup to see what kind of edit it is, validates input and saves it.
	private void saveChanges(){
		switch(popup.getId()){
		case "Name":
			if (!FieldVerifier.nameValid(txt_edited.getText())){
				Window.alert("Fejl - Du SKAL skrive et navn");
			}
			else{
				opr.setOprNavn(txt_edited.getText());
				txt_name.setText(opr.getOprNavn());
			}
			break;
		case "CPR":
			if(FieldVerifier.cprValid(txt_edited.getText())){
				opr.setCpr(txt_edited.getText());
				txt_CPRnr.setText(opr.getCpr());
			}
			else{
				Window.alert("FEJL - Forkert input");
			}
			break;
		case "Rolle":
			if(!FieldVerifier.rolleValid(Integer.parseInt(txt_edited.getText()))){
				Window.alert("Fejl - Forkert input");
			}
			else{
				opr.setRolle(Integer.parseInt(txt_edited.getText()));
				txt_role.setText(String.valueOf(opr.getRolle()));
			}
			break;
		case "Password":
			if(FieldVerifier.passwordValid(txt_edited.getText())){
				break;
			}
			Window.alert("FEJL - Password følger ikke reglerne");

		}
		serviceImpl.updateOperator(opr, new MyCallback());
	}
	private class MyCallback implements AsyncCallback<Boolean>{

		@Override
		public void onFailure(Throwable caught) {
			popup.setTitle("Fejl");
			
		}
		@Override
		public void onSuccess(Boolean result) {
			if(result){
			popup.toggle();
			}
			else{
				popup.setTitle("Fejl");
						
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
	private class EditNameClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Ændre navn");
			popup.setId("Name");
			txt_edited.setText(opr.getOprNavn());
			popup.toggle();		
		}	
	}
	private class EditCprClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Ændre CPR nummer");
			popup.setId("CPR");
			txt_edited.setText(FieldVerifier.cprFormat(opr.getCpr()));
			popup.toggle();		
		}	
	}
	private class EditRoleClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Ændre Rolle");
			popup.setId("Rolle");
			txt_edited.setText(String.valueOf(opr.getRolle()));
			popup.toggle();		
		}	
	}
	private class EditPasswordClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Ændre Password");
			popup.setId("Password");
			txt_edited.setText(opr.getPassword());
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
