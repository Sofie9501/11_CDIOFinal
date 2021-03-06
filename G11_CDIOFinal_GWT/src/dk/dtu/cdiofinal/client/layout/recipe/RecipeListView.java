package dk.dtu.cdiofinal.client.layout.recipe;

import java.util.ArrayList;
import java.util.List;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ButtonCell;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.github.gwtbootstrap.client.ui.resources.ButtonSize;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.cdiofinal.client.layout.AbstractView;
import dk.dtu.cdiofinal.client.layout.menu.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.recipe.ClientRecipeImpl;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public class RecipeListView extends AbstractView{

	private ProdView prod;

	private ClientRecipeImpl serviceImpl;
	private List<RecipeDTO> recipeList = new ArrayList<>();
	private ListDataProvider<RecipeDTO> dataProvider;
	private static RecipeListUiBinder uiBinder = GWT.create(RecipeListUiBinder.class);

	@UiTemplate("recipeListView.ui.xml")
	interface RecipeListUiBinder extends UiBinder<Widget, RecipeListView> {
	}

	//table and button
	@UiField CellTable<RecipeDTO> cellTable;
	@UiField Button btn_create;

	public RecipeListView(ProdView prod){
		initWidget(uiBinder.createAndBindUi(this));
		this.dataProvider = new ListDataProvider<RecipeDTO>();
		this.prod = prod;
		this.serviceImpl = new ClientRecipeImpl();

		//First column with ID
		TextColumn<RecipeDTO> IDColumn = new TextColumn<RecipeDTO>(){
			@Override
			public String getValue(RecipeDTO object) {
				return String.valueOf(object.getID());
			}
		};
		cellTable.addColumn(IDColumn, "Recipe ID");

		//Column with name of the recipe
		TextColumn<RecipeDTO> nameColumn = new TextColumn<RecipeDTO>(){
			@Override
			public String getValue(RecipeDTO object) {
				return (object.getName());
			}
		};
		cellTable.addColumn(nameColumn, "Recipe name");
		
		TextColumn<RecipeDTO> activeColumn = new TextColumn<RecipeDTO>(){
			@Override
			public String getValue(RecipeDTO object) {
				return String.valueOf(object.isActive());
			}
		};
		cellTable.addColumn(activeColumn, "Active");

		//Column with edit buttons
		Column<RecipeDTO, String> editColumn = new Column<RecipeDTO, String>(new ButtonCell(IconType.WRENCH,ButtonType.LINK, ButtonSize.SMALL)){
			@Override
			public String getValue(RecipeDTO object) {
				return "More...";
			}
		};
		cellTable.addColumn(editColumn);

		editColumn.setFieldUpdater(new FieldUpdater<RecipeDTO, String>(){
			//when edit is clicked view is set to recipedetailview
			@Override
			public void update(int index, RecipeDTO object, String value) {
				(RecipeListView.this).prod.setView(new RecipeDetailView((RecipeListView.this).prod, object));
			}

		});
		//show information in the table
		dataProvider.setList(recipeList); 
		dataProvider.addDataDisplay(cellTable);
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		btn_create.addClickHandler((ClickHandler)new CreateClickHandler());
		this.serviceImpl.getRecipies(new ListCallback());
	}

	//clickhandler for create new recipe
	private class CreateClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			prod.setView(new CreateRecipeView(prod));
		}
	}

	private class ListCallback implements AsyncCallback<List <RecipeDTO>>{
		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(List<RecipeDTO> result) {
			recipeList.clear();
			if(!result.isEmpty()){
				for(RecipeDTO d: result){
					recipeList.add(d);
				}
				dataProvider.refresh();
			}
		}
	}

	//updates the table
	@Override
	public void Update() {
		recipeList.clear();
		dataProvider.setList(recipeList);
		this.serviceImpl.getRecipies(new ListCallback());
	}

}
