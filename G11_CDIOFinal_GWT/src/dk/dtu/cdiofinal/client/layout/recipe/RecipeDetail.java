package dk.dtu.cdiofinal.client.layout.recipe;

import java.util.ArrayList;
import java.util.List;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ButtonCell;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.github.gwtbootstrap.client.ui.resources.ButtonSize;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
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

public class RecipeDetail extends AbstractView{

	private static RecipeDetailUiBinder uiBinder = GWT.create(RecipeDetailUiBinder.class);
	private ListDataProvider<RecipeComponentDTO> dataProvider;
	final ProdView prod;


	@UiTemplate("recipeDetail.ui.xml")
	interface RecipeDetailUiBinder extends UiBinder<Widget, RecipeDetail>{

	}
	private RecipeDTO rec;
	private ClientRecipeImpl serviceImpl;
	private int oldID;
	private List<RecipeComponentDTO> componentList = new ArrayList<>();

	//Adds the main page texts
	@UiField
	Heading txt_ID; 
	@UiField
	Heading txt_name;
	@UiField
	Heading txt_active;

	// Adds the edit buttons
	@UiField
	Button btn_ID;
	@UiField
	Button btn_active;
	@UiField
	Button btn_name;
	@UiField
	CellTable<RecipeComponentDTO> cellTable;
	@UiField
	Button add_component;

	@UiField
	Modal popup;
	@UiField
	TextBox txt_edited;
	@UiField
	Button btn_save;



	public RecipeDetail(ProdView prod, RecipeDTO object){
		this.prod=prod;
		initWidget(uiBinder.createAndBindUi(this));
		this.rec=object;
		dataProvider = new ListDataProvider<RecipeComponentDTO>();
		oldID = rec.getID();
		this.serviceImpl = new ClientRecipeImpl();
		//Adds all the information on the batch
		txt_ID.setText(String.valueOf(rec.getID()));
		txt_name.setText(rec.getName());
		txt_active.setText(String.valueOf(rec.isActive()));

		//Clickhandlers for all the buttons
		btn_ID.addClickHandler((ClickHandler)new Edit_IDClickHandler());
		btn_name.addClickHandler((ClickHandler)new EditNameClickHandler());
		btn_active.addClickHandler((ClickHandler) new EditActiveClickHandler());
		btn_save.addClickHandler((ClickHandler) new SaveClickHandler());
		txt_edited.addKeyDownHandler((KeyDownHandler) new EnterHandler());


		//First column with Ingredient ID
		TextColumn<RecipeComponentDTO> IDColumn = new TextColumn<RecipeComponentDTO>(){
			@Override
			public String getValue(RecipeComponentDTO object) {
				return String.valueOf(object.getIngredient_ID());
			}
		};
		
		cellTable.addColumn(IDColumn, "Ingredient ID");
		
		//Column with net
		TextColumn<RecipeComponentDTO> nameColumn = new TextColumn<RecipeComponentDTO>(){
			@Override
			public String getValue(RecipeComponentDTO object) {
				return object.getIngredientName();
			}
		};
		cellTable.addColumn(nameColumn, "Ingredient name");

		//Column with net
		TextColumn<RecipeComponentDTO> netColumn = new TextColumn<RecipeComponentDTO>(){
			@Override
			public String getValue(RecipeComponentDTO object) {
				return String.valueOf(object.getNom_netto());
			}
		};
		cellTable.addColumn(netColumn, "Net");
		

		//Column with net
		TextColumn<RecipeComponentDTO> tolColumn = new TextColumn<RecipeComponentDTO>(){
			@Override
			public String getValue(RecipeComponentDTO object) {
				return String.valueOf(object.getTolerance());
			}
		};
		cellTable.addColumn(tolColumn, "Tolerance");

		//Column with edit buttons
		Column<RecipeComponentDTO, String> editColumn = new Column<RecipeComponentDTO, String>(new ButtonCell(IconType.WRENCH,ButtonType.LINK, ButtonSize.SMALL)){
			@Override
			public String getValue(RecipeComponentDTO object) {
				return "More...";
			}
		};
		cellTable.addColumn(editColumn);

		editColumn.setFieldUpdater(new FieldUpdater<RecipeComponentDTO, String>(){

			@Override
			public void update(int index, RecipeComponentDTO object, String value) {
				(RecipeDetail.this).prod.setView(new RecipeComponentDetail(object));
			}

		});

		dataProvider.setList(componentList); 
		dataProvider.addDataDisplay(cellTable);
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		serviceImpl.getRecipeComponentList(object.getID(), new ListCallback());
		

	}
	//Checkes the ID of the popup to see what kind of edit it is, validates input and saves it.
	private void saveChanges(){
		switch(popup.getId()){
		case "Name":
			if(FieldVerifier.isValidName(txt_edited.getText())){
				rec.setName(txt_edited.getText());
				txt_name.setText(rec.getName());
			}
			else{
				Window.alert("Eroor - Wrong input");
			}
			break;
		case "ID":
			if(!FieldVerifier.numberValid(Integer.parseInt(txt_edited.getText()))){
				Window.alert("Error - ID input not valid");
			}
			else{
				rec.setID(Integer.parseInt(txt_edited.getText()));
				txt_ID.setText(String.valueOf(rec.getID()));
			}
			break;
		case "Active":
			if(FieldVerifier.active(Boolean.parseBoolean(txt_edited.getText()))){
				rec.setActive(Boolean.parseBoolean(txt_edited.getText()));
				txt_active.setText(String.valueOf(rec.isActive()));	
			}
			else{
				Window.alert("Error - Active input not valid");
			}
			break;
		}

		//update the recipe in DB
		serviceImpl.updateRecipe(rec, oldID, new MyCallback());
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
	private class Edit_IDClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change ID");
			popup.setId("ID");
			txt_edited.setText(String.valueOf(rec.getID()));
			popup.toggle();		
		}	
	}
	private class EditNameClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change name");
			popup.setId("Name");
			txt_edited.setText(rec.getName());
			popup.toggle();		
		}	
	}


	private class EditActiveClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			popup.setTitle("Change active status");
			popup.setId("Active");
			txt_edited.setText(String.valueOf((rec.isActive())));
			popup.toggle();		
		}	
	}
	private class SaveClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			saveChanges();
		}	
	}

	private class ListCallback implements AsyncCallback<List <RecipeComponentDTO>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Callback");
		}

		@Override
		public void onSuccess(List<RecipeComponentDTO> result) {
			componentList.clear();
			if(!result.isEmpty()){
				for(RecipeComponentDTO d: result){
					componentList.add(d);
				}
				dataProvider.refresh();
			}

		}
	}




	@Override
	public void Update() {
		componentList.clear();
		dataProvider.setList(componentList);
		this.serviceImpl.getRecipeComponentList(oldID, new ListCallback());
	}

}
