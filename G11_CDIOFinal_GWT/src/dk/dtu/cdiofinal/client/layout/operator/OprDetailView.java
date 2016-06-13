package dk.dtu.cdiofinal.client.layout.operator;

import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import dk.dtu.cdiofinal.client.layout.AbstractView;
import dk.dtu.cdiofinal.client.serverconnection.operator.ClientOperatorImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.OperatorDTO;

public class OprDetailView extends AbstractView{

	private static OprDetailUiBinder uiBinder = GWT.create(OprDetailUiBinder.class);


	@UiTemplate("oprdetail.ui.xml")
	interface OprDetailUiBinder extends UiBinder<Widget, OprDetailView>{

	}
	private OperatorDTO opr;
	private ClientOperatorImpl serviceImpl;
	private int oldID;

	//Adds the main page texts
	@UiField Heading txt_oprID; 
	@UiField Heading txt_name; 
	@UiField Heading txt_CPRnr; 
	@UiField Heading txt_role; 	
	@UiField Heading txt_password;
	@UiField Heading txt_active;

	// Adds the edit buttons
	@UiField
	Button btn_name;
	@UiField
	Button btn_CPR;
	@UiField
	Button btn_rolle;
	@UiField
	Button btn_password;
	@UiField
	Button btn_oprID;
	@UiField
	Button btn_active;

	//Add editpopup with save button
	@UiField
	Modal popup;
	@UiField
	TextBox txt_edited;
	@UiField
	Button btn_save;

	//Constuctor
	public OprDetailView(OperatorDTO opr) {
		this.opr=opr;
		oldID=opr.getOprID();
		initWidget(uiBinder.createAndBindUi(this));
		this.serviceImpl = new ClientOperatorImpl();

		//Adds all the information on the operators
		txt_oprID.setText(String.valueOf(opr.getOprID()));
		txt_name.setText(opr.getName());
		txt_CPRnr.setText(FieldVerifier.cprFormat(opr.getCpr()));
		txt_role.setText(String.valueOf(opr.getRole()));
		txt_password.setText(opr.getPassword());
		txt_active.setText(String.valueOf(opr.isActive()));

		//Clickhandlers for all the buttons
		btn_name.addClickHandler(new EditNameClickHandler());
		btn_CPR.addClickHandler(new EditCprClickHandler());
		btn_rolle.addClickHandler(new EditRoleClickHandler());
		btn_password.addClickHandler(new EditPasswordClickHandler());
		btn_oprID.addClickHandler(new EditIDClickHandler());
		btn_active.addClickHandler(new EditActiveClickHandler());
		btn_save.addClickHandler(new SaveClickHandler());
		txt_edited.addKeyDownHandler((KeyDownHandler) new EnterHandler());
	}
	//Checkes the ID of the popup to see what kind of edit it is, validates input and saves it.
	private void saveChanges(){
		switch(popup.getId()){
		case "Name":
			if (!FieldVerifier.nameValid(txt_edited.getText())){
				Window.alert("Error - You need to write a name");
			}
			else{
				opr.setName(txt_edited.getText());
				txt_name.setText(opr.getName());
			}
			break;
		case "ID":
			if (!FieldVerifier.numberValid(Integer.parseInt(txt_edited.getText()))){
				Window.alert("Error - ID not valid");
			}
			else{
				opr.setOprID(Integer.parseInt(txt_edited.getText()));
				txt_oprID.setText(String.valueOf(opr.getOprID()));
			}
			break;
		case "CPR":
			if(FieldVerifier.cprValid(txt_edited.getText())){
				opr.setCpr(txt_edited.getText());
				txt_CPRnr.setText(opr.getCpr());
			}
			else{
				Window.alert("Error - Wrong input");
			}
			break;
		case "Rolle":
			if(!FieldVerifier.roleValid(Integer.parseInt(txt_edited.getText()))){
				Window.alert("Error - Wrong input");
			}
			else{
				opr.setRole(Integer.parseInt(txt_edited.getText()));
				txt_role.setText(String.valueOf(opr.getRole()));
			}
			break;
		case "active":
			if(!FieldVerifier.active(Boolean.parseBoolean(txt_edited.getText()))){
				Window.alert("Error - Wrong input");
			}
			else{
				opr.setActive(Boolean.parseBoolean(txt_edited.getText()));
				txt_active.setText(String.valueOf(opr.isActive()));
			}
			break;
		case "Password":
			if(FieldVerifier.passwordValid(txt_edited.getText())){
				opr.setPassword(txt_edited.getText());
				txt_password.setText(opr.getPassword());
			}
			else
				Window.alert("Error - Password does  not follow the rules");

		}
		serviceImpl.updateOperator(opr, oldID, new MyCallback());
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
	private class EditNameClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change Name");
			popup.setId("Name");
			txt_edited.setText(opr.getName());
			popup.toggle();		
		}	
	}
	private class EditIDClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change ID");
			popup.setId("ID");
			txt_edited.setText(String.valueOf(opr.getOprID()));
			popup.toggle();		
		}	
	}
	private class EditCprClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change CPR number");
			popup.setId("CPR");
			txt_edited.setText(FieldVerifier.cprFormat(opr.getCpr()));
			popup.toggle();		
		}	
	}
	private class EditRoleClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change Role");
			popup.setId("Rolle");
			txt_edited.setText(String.valueOf(opr.getRole()));
			popup.toggle();		
		}	
	}
	private class EditPasswordClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change Password");
			popup.setId("Password");
			txt_edited.setText(opr.getPassword());
			popup.toggle();		
		}	
	}
	private class EditActiveClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change active");
			popup.setId("active");
			txt_edited.setText(String.valueOf(opr.isActive()));
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
