package dk.dtu.cdiofinal.client.layout.ingredient;

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
import dk.dtu.cdiofinal.client.layout.ingredientbatch.CertainIngredientBatchListView;
import dk.dtu.cdiofinal.client.layout.menu.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.ingredient.ClientIngredientImpl;
import dk.dtu.cdiofinal.shared.IngredientDTO;



public class IngredientListView extends AbstractView {
	
	final ProdView prod;
	private ClientIngredientImpl serviceImpl;
	private List<IngredientDTO> ingredientList = new ArrayList<>();
	private ListDataProvider<IngredientDTO> dataProvider;
	private static IngredientListViewUiBinder uiBinder = GWT.create(IngredientListViewUiBinder.class);
	
	@UiTemplate("ingredientListView.ui.xml")
	interface IngredientListViewUiBinder  extends UiBinder<Widget, IngredientListView> {
	}

	//table and buttin
	@UiField CellTable<IngredientDTO> cellTable;
	@UiField Button btn_create;

	public IngredientListView(ProdView prod){
		initWidget(uiBinder.createAndBindUi(this));
		this.dataProvider = new ListDataProvider<IngredientDTO>();
		this.prod = prod;
		this.serviceImpl = new ClientIngredientImpl();

		//Coumn with Id
		TextColumn<IngredientDTO> IDColumn = new TextColumn<IngredientDTO>(){
			@Override
			public String getValue(IngredientDTO object) {
				return String.valueOf(object.getID());
			}
		};
		cellTable.addColumn(IDColumn, "Ingredient ID");
		//coimn with name
		TextColumn<IngredientDTO> nameColumn = new TextColumn<IngredientDTO>(){
			@Override
			public String getValue(IngredientDTO object) {
				return object.getName();
			}
		};
		cellTable.addColumn(nameColumn, "Ingredient name");
		//coumn with supplier
		TextColumn<IngredientDTO> supplierColumn = new TextColumn<IngredientDTO>(){
			@Override
			public String getValue(IngredientDTO object) {
				return object.getSupplier();
			}
		};
		cellTable.addColumn(supplierColumn, "Suppiler");	
		//coumn with active
		TextColumn<IngredientDTO> activeColumn = new TextColumn<IngredientDTO>(){
			@Override
			public String getValue(IngredientDTO object) {
				return String.valueOf(object.isActive());
			}
		};
		cellTable.addColumn(activeColumn, "Active");	
		//coumn with se batches buttons
		Column<IngredientDTO, String> seeBatchColumn = new Column<IngredientDTO, String>(new ButtonCell(IconType.PLUS,ButtonType.LINK, ButtonSize.SMALL)){
			@Override
			public String getValue(IngredientDTO object) {
				return "See batches";
			}
		};
		cellTable.addColumn(seeBatchColumn);
		seeBatchColumn.setFieldUpdater(new FieldUpdater<IngredientDTO, String>(){
			//chages view to certainIngrecientbatchListView
			@Override
			public void update(int index, IngredientDTO object, String value) {
				(IngredientListView.this).prod.setView(new CertainIngredientBatchListView((IngredientListView.this).prod, object.getID()));
			}
		});
		//coumn with edit buttons
		Column<IngredientDTO, String> editColumn = new Column<IngredientDTO, String>(new ButtonCell(IconType.WRENCH,ButtonType.LINK, ButtonSize.SMALL)){
			@Override
			public String getValue(IngredientDTO object) {
				return "More...";
			}
		};

		cellTable.addColumn(editColumn);
		
		editColumn.setFieldUpdater(new FieldUpdater<IngredientDTO, String>(){
			//when edit is clicked view is changed to ingredientdetailview
			@Override
			public void update(int index, IngredientDTO object, String value) {
				(IngredientListView.this).prod.setView(new IngredientDetailView(object));
			}

		});
		//show the infromation in the table
		dataProvider.setList(ingredientList); 
		dataProvider.addDataDisplay(cellTable);
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		btn_create.addClickHandler((ClickHandler)new CreateClickHandler());
		this.serviceImpl.getIngredients(new ListCallback());
	}
	
	//click handler when create is clicked
	private class CreateClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			prod.setView(new CreateIngredientView(prod));
		}
	}

	private class ListCallback implements AsyncCallback<List <IngredientDTO>>{

		@Override
		public void onFailure(Throwable caught) {
		}
		
		@Override
		public void onSuccess(List<IngredientDTO> result) {
			ingredientList.clear();
			if(!result.isEmpty()){
				for(IngredientDTO d: result){
					ingredientList.add(d);
				}
				dataProvider.refresh();
			}
				
		}
	}
	
	//updates the table
	@Override
	public void Update() {
		ingredientList.clear();
		dataProvider.setList(ingredientList);
		this.serviceImpl.getIngredients(new ListCallback());
	}

}
