package dk.dtu.cdiofinal.client.layout.productbatch;

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
import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.layout.Menu.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.productbatch.ClientProductBatchImpl;
import dk.dtu.cdiofinal.shared.IngredientDTO;
import dk.dtu.cdiofinal.shared.ProductBatchDTO;



public class ProductBatchListView extends AbstractView {

	final ProdView prod;

	private ClientProductBatchImpl serviceImpl;
	private List<ProductBatchDTO> list = new ArrayList<>();
	private ListDataProvider<ProductBatchDTO> dataProvider;
	private static ProductBatchListViewUiBinder uiBinder = GWT.create(ProductBatchListViewUiBinder.class);

	@UiTemplate("productBatchListView.ui.xml")
	interface ProductBatchListViewUiBinder extends UiBinder<Widget, ProductBatchListView> {
	}

	// Table
	@UiField
	CellTable<ProductBatchDTO> cellTable;


	@UiField
	Button btn_create;


	public ProductBatchListView(ProdView prod) {
		initWidget(uiBinder.createAndBindUi(this));
		this.dataProvider = new ListDataProvider<ProductBatchDTO>();
		this.prod = prod;
		this.serviceImpl = new ClientProductBatchImpl();

		// First column shows ProductBatch ID
		TextColumn<ProductBatchDTO> IDProductBatch = new TextColumn<ProductBatchDTO>(){
			@Override
			public String getValue(ProductBatchDTO object){
				return String.valueOf(object.getPb_ID());
			}
		};
		cellTable.addColumn(IDProductBatch, "Product Batch ID");

		// column there shows ingredient ID
		TextColumn<ProductBatchDTO> RecipeID = new TextColumn<ProductBatchDTO>(){
			@Override
			public String getValue(ProductBatchDTO object) {
				return String.valueOf(object.getR_ID());
			}
		};
		cellTable.addColumn(RecipeID, "Recipe ID");
		
		// Column with name of the productBatch
		TextColumn<ProductBatchDTO> nameColumn = new TextColumn<ProductBatchDTO>(){
			@Override
			public String getValue(ProductBatchDTO object) {
				return object.getName();
			}
		};
		cellTable.addColumn(nameColumn, "Recipe name");



		//		// Column there tells if it is active
		//		TextColumn<ProductBatchDTO> active  = new TextColumn<ProductBatchDTO>(){
		//			@Override
		//			public String getValue(ProductBatchDTO object) {
		//				return String.valueOf(object.isActive());
		//			}
		//		};
		//		cellTable.addColumn(active);

		// Column there shows how many there is finished
		TextColumn<ProductBatchDTO> countFinished = new TextColumn<ProductBatchDTO>(){
			@Override
			public String getValue(ProductBatchDTO object) {
				return String.valueOf(object.getCountFinished());
			}
		};
		cellTable.addColumn(countFinished, "Finished");
		
		// Column there counts components
		TextColumn<ProductBatchDTO> countComponents = new TextColumn<ProductBatchDTO>(){
			@Override
			public String getValue(ProductBatchDTO object) {
				return String.valueOf(object.getCountComponents());
			}
		};
		cellTable.addColumn(countComponents, "Total");

		//		// Column there shows the status
		//		TextColumn<ProductBatchDTO> status = new TextColumn<ProductBatchDTO>(){
		//			@Override
		//			public String getValue(ProductBatchDTO object) {
		//				return String.valueOf(object.getStatus());
		//			}
		//		};
		//		cellTable.addColumn(status);

		// Column with start date
		TextColumn<ProductBatchDTO> startDate = new TextColumn<ProductBatchDTO>(){
			@Override
			public String getValue(ProductBatchDTO object) {
				return String.valueOf(object.getStart_date());
			}
		};
		cellTable.addColumn(startDate, "Start date");
		
		// Column there shows the end date
		TextColumn<ProductBatchDTO> endDate = new TextColumn<ProductBatchDTO>(){
			@Override
			public String getValue(ProductBatchDTO object) {
				return String.valueOf(object.getEnd_date());
			}
		};

		cellTable.addColumn(endDate, "End date");
		
		TextColumn<ProductBatchDTO> activeColumn = new TextColumn<ProductBatchDTO>(){
			@Override
			public String getValue(ProductBatchDTO object) {
				return String.valueOf(object.isActive());
			}
		};
		cellTable.addColumn(activeColumn, "Active");




		//Column with edit button
		Column<ProductBatchDTO, String> editColumn = new Column<ProductBatchDTO, String>(new ButtonCell(IconType.WRENCH,ButtonType.LINK, ButtonSize.SMALL)){
			@Override
			public String getValue(ProductBatchDTO object) {
				return "More...";
			}
		};
		cellTable.addColumn(editColumn);

		editColumn.setFieldUpdater(new FieldUpdater<ProductBatchDTO, String>(){

			@Override
			public void update(int index, ProductBatchDTO object, String value) {
				(ProductBatchListView.this).prod.setView(new ProductBatchDetailView(object));
			}
		});

		dataProvider.setList(list);
		dataProvider.addDataDisplay(cellTable);
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		btn_create.addClickHandler((ClickHandler)new CreateClickHandler());
		this.serviceImpl.getProductBatches(new ListCallback());
	}

	// Clickhandler for creating new ProductBatch
	private class CreateClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			prod.setView(new CreateProductBatchView(prod));
		}
	}

	private class ListCallback implements AsyncCallback<List <ProductBatchDTO>>{

		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(List<ProductBatchDTO> result) {
			list.clear();
			if(!result.isEmpty()){
				for(ProductBatchDTO d: result){
					list.add(d);
				}
				dataProvider.refresh();
			}

		}
	}
	@Override
	public void Update() {
		list.clear();
		dataProvider.setList(list);
		this.serviceImpl.getProductBatches(new ListCallback());
	}

}
