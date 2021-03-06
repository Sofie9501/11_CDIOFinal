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

import dk.dtu.cdiofinal.client.layout.AbstractView;
import dk.dtu.cdiofinal.client.serverconnection.ingredientbatch.ClientIngredientBatchImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

public class IngredientBatchDetailView extends AbstractView{

	private static IngredientBatchDetailUiBinder uiBinder = GWT.create(IngredientBatchDetailUiBinder.class);
	private IngredientBatchDTO ingredientBatch;
	private ClientIngredientBatchImpl serviceImpl;
	private int oldID;

	@UiTemplate("ingredientBatchDetail.ui.xml")
	interface IngredientBatchDetailUiBinder extends UiBinder<Widget, IngredientBatchDetailView>{

	}
	

	//Adds the main page texts
	@UiField Heading txt_IngredientBatch_Id; 
	@UiField Heading txt_name; 
	@UiField Heading txt_Ingredient_Id; 
	@UiField Heading txt_amount; 	
	@UiField Heading txt_date;
	@UiField Heading txt_active;
	@UiField Button btn_IngredientBatch_Id;
	@UiField Button btn_Ingredient_Id;
	@UiField Button btn_amount;
	@UiField Button btn_active;
	@UiField Button btn_name;
	@UiField Modal popup;
	@UiField TextBox txt_edited;
	@UiField Button btn_save;


	public IngredientBatchDetailView(IngredientBatchDTO object){
		this.ingredientBatch=object;
		oldID = object.getIngredientBatch_ID();
		initWidget(uiBinder.createAndBindUi(this));
		this.serviceImpl = new ClientIngredientBatchImpl();

		//Adds all the information on the batch
		txt_IngredientBatch_Id.setText(String.valueOf(ingredientBatch.getIngredientBatch_ID()));
		txt_name.setText(ingredientBatch.getName());
		txt_Ingredient_Id.setText(String.valueOf(ingredientBatch.getIngredient_ID()));
		txt_amount.setText(String.valueOf(ingredientBatch.getAmount()));
		txt_date.setText(ingredientBatch.getDate());
		txt_active.setText(String.valueOf(ingredientBatch.isActive()));

		//Clickhandlers for all the buttons
		btn_IngredientBatch_Id.addClickHandler((ClickHandler)new EditIB_IDClickHandler());
		btn_name.addClickHandler((ClickHandler)new EditNameClickHandler());
		btn_Ingredient_Id.addClickHandler((ClickHandler) new EditIN_IDClickHandler());
		btn_amount.addClickHandler((ClickHandler) new EditAmountClickHandler());
		btn_active.addClickHandler((ClickHandler) new EditActiveClickHandler());
		btn_save.addClickHandler((ClickHandler) new SaveClickHandler());
		txt_edited.addKeyDownHandler((KeyDownHandler) new EnterHandler());
	}
	//Checkes the ID of the popup to see what kind of edit it is, validates input and saves it.
	private void saveChanges(){
		switch(popup.getId()){
		case "IB_ID":
			if (!FieldVerifier.numberValid(Integer.parseInt(txt_edited.getText()))){
				Window.alert("Error - ID input not valid");
			}
			else{
				ingredientBatch.setIngredientBatch_ID(Integer.parseInt(txt_edited.getText()));
				txt_IngredientBatch_Id.setText(String.valueOf(ingredientBatch.getIngredientBatch_ID()));
			}
			break;
		case "Name":
			if(FieldVerifier.nameValid(txt_edited.getText())){
				ingredientBatch.setName(txt_edited.getText());
				txt_name.setText(ingredientBatch.getName());
			}
			else{
				Window.alert("Error - Wrong input");
			}
			break;
		case "ID":
			if(!FieldVerifier.numberValid(Integer.parseInt(txt_edited.getText()))){
				Window.alert("Error - ID input not valid");
			}
			else{
				ingredientBatch.setIngredient_ID(Integer.parseInt(txt_edited.getText()));
				txt_Ingredient_Id.setText(String.valueOf(ingredientBatch.getIngredient_ID()));
			}
			break;
		case "Active":
			if(txt_edited.getText().equals(true)||txt_edited.getText().equals(false)){
				ingredientBatch.setActive(Boolean.parseBoolean(txt_edited.getText()));
				txt_active.setText(String.valueOf(ingredientBatch.isActive()));	
			}
			else{
				Window.alert("Error - Active input not valid");
			}
			break;

		case "Amount":
			ingredientBatch.setAmount(Double.parseDouble(txt_edited.getText()));
			txt_amount.setText(String.valueOf(ingredientBatch.getAmount()));
			break;
		}
		//update the batch in DB
		serviceImpl.updateIngredientBatch(ingredientBatch, oldID, new UpdateIngredientBatchCallback());
	}
	
	
	private class UpdateIngredientBatchCallback implements AsyncCallback<Boolean>{

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
	private class EditIB_IDClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change ID");
			popup.setId("IB_ID");
			txt_edited.setText(String.valueOf(ingredientBatch.getIngredientBatch_ID()));
			popup.toggle();		
		}	
	}
	private class EditNameClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change name");
			popup.setId("Name");
			txt_edited.setText(ingredientBatch.getName());
			popup.toggle();		
		}	
	}

	private class EditIN_IDClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change ingredient ID");
			popup.setId("ID");
			txt_edited.setText(String.valueOf((ingredientBatch.getIngredient_ID())));
			popup.toggle();		
		}	
	}
	private class EditAmountClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change amount");
			popup.setId("Amount");
			txt_edited.setText(String.valueOf((ingredientBatch.getAmount())));
			popup.toggle();		
		}	
	}
	private class EditActiveClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change active status");
			popup.setId("Active");
			txt_edited.setText(String.valueOf((ingredientBatch.isActive())));
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
}
