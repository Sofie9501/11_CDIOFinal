package dk.dtu.cdiofinal.client.layout.ingredientbatch;

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
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.layout.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.ingredientbatch.ClientIngredientBatchImpl;
import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

public class CertainIngredientBatchListView extends AbstractView{
	final ProdView prod;
	
	private ClientIngredientBatchImpl serviceImpl;
	private int cID;
	private List<IngredientBatchDTO> inb = new ArrayList<>();
	private ListDataProvider<IngredientBatchDTO> dataProvider;
	private static CertainIngredientBatchListUiBinder uiBinder = GWT.create(CertainIngredientBatchListUiBinder.class);

	@UiTemplate("certainIngredientBatchListView.ui.xml")
	interface CertainIngredientBatchListUiBinder extends UiBinder<Widget, CertainIngredientBatchListView> {
	}

	//table
	@UiField
	CellTable<IngredientBatchDTO> cellTable;

	//to create a new batch
	@UiField
	Button btn_create;

	public CertainIngredientBatchListView(ProdView prod, int cID){
		this.prod = prod;
		this.cID = cID;
		initWidget(uiBinder.createAndBindUi(this));
		this.dataProvider = new ListDataProvider<IngredientBatchDTO>();
		this.serviceImpl = new ClientIngredientBatchImpl();

		//First column with ID
		TextColumn<IngredientBatchDTO> IDColumn = new TextColumn<IngredientBatchDTO>(){
			@Override
			public String getValue(IngredientBatchDTO object) {
				return String.valueOf(object.getIngredientBatch_ID());
			}
		};
		cellTable.addColumn(IDColumn);

		//Column with name of the ingredient
		TextColumn<IngredientBatchDTO> nameColumn = new TextColumn<IngredientBatchDTO>(){
			@Override
			public String getValue(IngredientBatchDTO object) {
				return (object.getName());
			}
		};
		cellTable.addColumn(nameColumn);

		//First column with ingredient ID
		TextColumn<IngredientBatchDTO> ID = new TextColumn<IngredientBatchDTO>(){
			@Override
			public String getValue(IngredientBatchDTO object) {
				return String.valueOf(object.getIngredient_ID());
			}
		};
		cellTable.addColumn(ID);
		
		//Column with amount
		TextColumn<IngredientBatchDTO> amountColumn = new TextColumn<IngredientBatchDTO>(){
			@Override
			public String getValue(IngredientBatchDTO object) {
				return String.valueOf((object.getAmount()));
			}
		};
		cellTable.addColumn(amountColumn);		
	

		//column with date
		TextColumn<IngredientBatchDTO> dateColumn = new TextColumn<IngredientBatchDTO>(){
			@Override
			public String getValue(IngredientBatchDTO object) {
				return object.getDate();
			}
		};
		cellTable.addColumn(dateColumn);	

		//Column with edit buttons
		Column<IngredientBatchDTO, String> editColumn = new Column<IngredientBatchDTO, String>(new ButtonCell(IconType.WRENCH,ButtonType.LINK, ButtonSize.SMALL)){
			@Override
			public String getValue(IngredientBatchDTO object) {
				return "More...";
			}
		};
		cellTable.addColumn(editColumn);

		editColumn.setFieldUpdater(new FieldUpdater<IngredientBatchDTO, String>(){

			@Override
			public void update(int index, IngredientBatchDTO object, String value) {
				(CertainIngredientBatchListView.this).prod.setView(new IngredientBatchDetail(object));
			}

		});
		dataProvider.setList(inb); 
		dataProvider.addDataDisplay(cellTable);
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		btn_create.addClickHandler((ClickHandler)new CreateClickHandler());
		this.serviceImpl.getIngredientBatchesList(cID, new ListCallback());
	}

	//clickhandler for create new batch
	private class CreateClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			(CertainIngredientBatchListView.this).prod.setView(new CreateCertainIngredientBatch(prod, cID));
		}
	}

	private class ListCallback implements AsyncCallback<List <IngredientBatchDTO>>{

		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(List<IngredientBatchDTO> result) {
			inb.clear();
			if(!result.isEmpty()){
				for(IngredientBatchDTO d: result){
					inb.add(d);
				}
				dataProvider.refresh();
			}

		}
	}

	@Override
	public void Update() {
		inb.clear();
		dataProvider.setList(inb);
		this.serviceImpl.getIngredientBatches(new ListCallback());
	}
}
