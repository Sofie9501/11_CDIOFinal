package dk.dtu.cdiofinal.client.layout.ingredient;

import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.serverconnection.operator.ClientOperatorImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.IngredientDTO;

public class IngredientDetail extends AbstractView {
	private static IngredientDetailUiBinder uiBinder = GWT.create(IngredientDetailUiBinder.class);
	
	@UiTemplate("ingredientDetail.ui.xml")
	interface IngredientDetailUiBinder extends UiBinder<Widget, CreateIngredientView>{
	}
	
	IngredientDTO ingredient;
	ClientOperatorImpl serviceImpl;
	
	@UiField
	Heading txt_ID; 
	@UiField
	Heading txt_name; 
	@UiField
	Heading txt_supplier; 
	@UiField
	Heading txt_active;
	
	@UiField
	Button btn_ID;
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
		initWidget(uiBinder.createAndBindUi(this));
		this.serviceImpl = new ClientOperatorImpl();
		
		txt_ID.setText(String.valueOf(ingredient.getID()));
		txt_name.setText(ingredient.getName());
		txt_supplier.setText(ingredient.getSupplier());
		txt_active.setText(String.valueOf(ingredient.isActive()));
		
		btn_name.addClickHandler((ClickHandler)new EditNameClickHandler());
		btn_ID.addClickHandler((ClickHandler) new EditCprClickHandler());
		btn_supplier.addClickHandler((ClickHandler) new EditRoleClickHandler());
		btn_active.addClickHandler((ClickHandler) new EditPasswordClickHandler());
		btn_save.addClickHandler((ClickHandler) new SaveClickHandler());
		txt_edited.addKeyDownHandler((KeyDownHandler) new EnterHandler());
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
				popup.setTitle("Ã†ndre navn");
				popup.setId("Name");
				txt_edited.setText(opr.getOprNavn());
				popup.toggle();		
			}	
		}
		private class EditCprClickHandler implements ClickHandler{
			@Override
			public void onClick(ClickEvent event) {
				popup.setTitle("Ã†ndre CPR nummer");
				popup.setId("CPR");
				txt_edited.setText(FieldVerifier.cprFormat(opr.getCpr()));
				popup.toggle();		
			}	
		}
		private class EditRoleClickHandler implements ClickHandler{
			@Override
			public void onClick(ClickEvent event) {
				popup.setTitle("Ã†ndre Rolle");
				popup.setId("Rolle");
				txt_edited.setText(String.valueOf(opr.getRolle()));
				popup.toggle();		
			}	
		}
		private class EditPasswordClickHandler implements ClickHandler{
			@Override
			public void onClick(ClickEvent event) {
				popup.setTitle("Ã†ndre Password");
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
