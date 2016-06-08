package dk.dtu.cdiofinal.client.layout.recipe;

import java.util.ArrayList;
import java.util.List;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.CellTable;
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
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.layout.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.recipe.ClientRecipeImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public class CreateRecipe extends AbstractView {

	final ProdView prod;
	protected ClientRecipeImpl serviceImpl;
	private static createRecipeUiBinder uiBinder = GWT.create(createRecipeUiBinder.class);
	private RecipeDTO batch;
	private ArrayList<RecipeComponentDTO> list = new ArrayList<>();
	private ListDataProvider<RecipeComponentDTO> dataProvider;

	@UiTemplate("createRecipe.ui.xml")
	interface createRecipeUiBinder extends UiBinder<Widget, CreateRecipe>{

	}

	//Textbox, buttons, heading and modal
	@UiField
	TextBox txt_ID;
	@UiField
	TextBox txt_name;
	@UiField
	Button btn_save;
	@UiField
	Button btn_add;
	@UiField
	Button btn_save_comp;

	@UiField
	TextBox txt_IN_ID;
	@UiField
	TextBox txt_IN_name;
	@UiField
	TextBox txt_Net;
	@UiField
	TextBox txt_Tol;


	@UiField
	Modal popup;
	@UiField
	Button btn_ok;
	@UiField
	Heading ok;
	@UiField
	CellTable<RecipeComponentDTO> cellTable;



	public CreateRecipe(ProdView prod){
		initWidget(uiBinder.createAndBindUi(this));
		this.prod=prod;
		this.serviceImpl = new ClientRecipeImpl();
		cellTable.setVisible(false);
		txt_IN_ID.setVisible(false);
		txt_IN_name.setVisible(false);
		txt_Net.setVisible(false);
		txt_Tol.setVisible(false);
		btn_save_comp.setVisible(false);
		//Add click and key handler to buttons and last textbox
		btn_save.addClickHandler(new SaveClickHandler());
		btn_ok.addClickHandler((ClickHandler)new OkClickHandler());
		btn_add.addClickHandler(new AddClickHandler());
		txt_name.addKeyDownHandler((KeyDownHandler)new EnterHandler());
		btn_save_comp.addClickHandler(new SaveCompClickHandler());



	}
	private boolean changeSucces(){
		String alert = "";
		boolean succes = true;
		if(!FieldVerifier.numberValid(Integer.parseInt(txt_ID.getText()))){
			alert+="Error - You need to write a valid batch ID \n";
			succes = false;
		}
		if(!FieldVerifier.isValidName(txt_name.getText())){
			alert += "Fejl - Name for Reipe is not valid \n";
			succes = false;
		}
		if(!alert.equals(""))
			Window.alert(alert);
		return succes;
	}
	private void saveChanges(){
		// Checks to see if there is no errors
		if(changeSucces()){
			batch = new RecipeDTO(Integer.parseInt(txt_ID.getText()), txt_name.getText(), true);
			ok.setText("Your information has been saved");
			//Updates the DB with the new operator
			serviceImpl.createRecipe(batch, list, new MyCallback());

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


	private class AddClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			txt_IN_ID.setVisible(true);
			txt_IN_ID.setText("Ingredient ID");
			txt_IN_name.setVisible(true);
			txt_IN_name.setText("Ingredient Name");
			txt_Net.setVisible(true);
			txt_Net.setText("Nom_net");
			txt_Tol.setVisible(true);
			txt_Tol.setText("Tolerance");
			btn_save_comp.setVisible(true);
			cellTable.setVisible(true);
		}
	}

	private class SaveCompClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			RecipeComponentDTO dto = new RecipeComponentDTO(Integer.parseInt(txt_ID.getText()), 
					Integer.parseInt(txt_IN_ID.getText()), txt_IN_name.getText(), 
					(Double.parseDouble(txt_Net.getText())),
					(Double.parseDouble(txt_Tol.getText())));
			list.add(dto);
			txt_IN_ID.setVisible(false);
			txt_IN_name.setVisible(false);
			txt_Net.setVisible(false);
			txt_Tol.setVisible(false);
			btn_save_comp.setVisible(false);
			list.clear();
			dataProvider.setList(list);
			cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
			serviceImpl.getRecipiesComp(list, new ListCallback());
		}

	}
	
	private class ListCallback implements AsyncCallback<List <RecipeComponentDTO>>{

		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(List<RecipeComponentDTO> result) {
			list.clear();
			if(!result.isEmpty()){
				for(RecipeComponentDTO d: result){
					list.add(d);
				}
				dataProvider.refresh();
			}

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
				txt_ID.setText("");
				txt_name.setText("");
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
