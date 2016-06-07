package dk.dtu.cdiofinal.client.layout.productbatch;

import java.util.ArrayList;
import java.util.List;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.layout.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.productbatch.ClientProductBatchImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.IngredientDTO;
import dk.dtu.cdiofinal.shared.OperatorDTO;
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

		// First column with ID
		TextColumn<ProductBatchDTO> IDColumn = new TextColumn<ProductBatchDTO>(){
			@Override
			public String getValue(ProductBatchDTO object){
				return String.valueOf(object.getPb_ID());
			}
		};
		cellTable.addColumn(IDColumn);

		// Column with name of the productBatch
		TextColumn<ProductBatchDTO> nameColumn = new TextColumn<ProductBatchDTO>(){
			@Override
			public String getValue(ProductBatchDTO object) {
				return object.getName();
			}
		};
		cellTable.addColumn(nameColumn);
		
		// Column with start date
		TextColumn<ProductBatchDTO> startDate = new TextColumn<ProductBatchDTO>(){
			@Override
			public String getValue(ProductBatchDTO object) {
				return String.valueOf(object.getStart_date());
			}
		};
		cellTable.addColumn(startDate);
		
		// JEG ER NÅET HER TIL :::DW:DW:F:W:FW:FW:
		
		
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

}
