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

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.serverconnection.ingredient.ClientIngredientImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.IngredientDTO;

public class IngredientDetail extends AbstractView {
	private static IngredientDetailUiBinder uiBinder = GWT.create(IngredientDetailUiBinder.class);

	@UiTemplate("ingredientDetail.ui.xml")
	interface IngredientDetailUiBinder extends UiBinder<Widget, IngredientDetail>{
	}

	private IngredientDTO ingredient;
	private ClientIngredientImpl serviceImpl;
	private int oldID;

	@UiField
	Heading txt_id; 
	@UiField
	Heading txt_name; 
	@UiField
	Heading txt_supplier; 
	@UiField
	Heading txt_active;

	@UiField
	Button btn_id;
	@UiField
	Button btn_name;
	@UiField
	Button btn_supplier;
	@UiField
	Button btn_active;

	@UiField
	Modal popup;
	@UiField
	TextBox txt_edited;
	@UiField
	Button btn_save;

	public IngredientDetail(IngredientDTO ingredient) {
		this.ingredient = ingredient;
		oldID=ingredient.getID();
		initWidget(uiBinder.createAndBindUi(this));
		this.serviceImpl = new ClientIngredientImpl();

		txt_id.setText(String.valueOf(ingredient.getID()));
		txt_name.setText(ingredient.getName());
		txt_supplier.setText(ingredient.getSupplier());
		txt_active.setText(String.valueOf(ingredient.isActive()));

		btn_name.addClickHandler(new EditNameClickHandler());
		btn_id.addClickHandler(new EditIDClickHandler());
		btn_supplier.addClickHandler(new EditSupplierClickHandler());
		btn_active.addClickHandler(new EditActiveClickHandler());
		btn_save.addClickHandler(new SaveClickHandler());
		txt_edited.addKeyDownHandler((KeyDownHandler) new EnterHandler());
	}

	// Makes it possible to hit ENTER instead of the Save button.
	private class EnterHandler implements KeyDownHandler {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
				saveChanges();
			}		
		}	
	}
	//Click handlers for all the different buttons.
	private class EditNameClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change Name");
			popup.setId("Name");
			txt_edited.setText(ingredient.getName());
			popup.toggle();		
		}	
	}
	private class EditIDClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change ID");
			popup.setId("ID");
			txt_edited.setText(String.valueOf(ingredient.getID()));
			popup.toggle();		
		}	
	}
	private class EditSupplierClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change Supplier");
			popup.setId("supplier");
			txt_edited.setText(ingredient.getSupplier());
			popup.toggle();		
		}	
	}
	private class EditActiveClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change active status");
			popup.setId("Active");
			txt_edited.setText(String.valueOf(ingredient.isActive()));
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

	}
	
	private void saveChanges(){
		switch(popup.getId()){
		case "Name":
			if (!FieldVerifier.nameValid(txt_edited.getText())){
				Window.alert("Error - You need to write a name");
			}
			else{
				ingredient.setName(txt_edited.getText());
				txt_name.setText(ingredient.getName());
			}
			break;
		case "ID":
			if(FieldVerifier.numberValid(Integer.parseInt(txt_edited.getText()))){
				ingredient.setID(Integer.parseInt((txt_edited.getText())));
				txt_id.setText(String.valueOf(ingredient.getID()));
			}
			else{
				Window.alert("Error - Wrong input");
			}
			break;
		case "supplier":
			if (!FieldVerifier.nameValid(txt_edited.getText())){
				Window.alert("Error - You have to write a name");
			}
			else{
				ingredient.setSupplier((txt_edited.getText()));
				txt_name.setText(ingredient.getName());
			}
			break;
		case "active":
			if(txt_edited.getText().equals(true)||txt_edited.getText().equals(false)){
				ingredient.setActive(Boolean.parseBoolean(txt_edited.getText()));
				txt_active.setText(String.valueOf(ingredient.isActive()));	
			}
			else{
				Window.alert("Error - Active input not valid");
			}
			break;
		}
		
		serviceImpl.updateIngredient(ingredient, oldID, new MyCallback());
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

}
