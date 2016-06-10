package dk.dtu.cdiofinal.client.layout.operator;

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
import dk.dtu.cdiofinal.client.layout.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.operator.ClientOperatorImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.OperatorDTO;



public class OprListView extends AbstractView {
	
	final ProdView prod;

	private ClientOperatorImpl serviceImpl;
	private List<OperatorDTO> opr = new ArrayList<>();
	ListDataProvider<OperatorDTO> dataProvider;
	private static OprListViewUiBinder uiBinder = GWT.create(OprListViewUiBinder.class);

	@UiTemplate("oprListView.ui.xml")
	interface OprListViewUiBinder extends UiBinder<Widget, OprListView> {
	}

	@UiField
	CellTable<OperatorDTO> cellTable;

	@UiField
	Button btn_create;

	public OprListView(ProdView prod){
		initWidget(uiBinder.createAndBindUi(this));
		this.dataProvider = new ListDataProvider<OperatorDTO>();
		this.prod = prod;
		this.serviceImpl = new ClientOperatorImpl();

		
		
		TextColumn<OperatorDTO> IDColumn = new TextColumn<OperatorDTO>(){
			@Override
			public String getValue(OperatorDTO object) {
				return String.valueOf(object.getOprID());
			}
		};
		
		cellTable.addColumn(IDColumn, "ID");

		TextColumn<OperatorDTO> nameColumn = new TextColumn<OperatorDTO>(){
			@Override
			public String getValue(OperatorDTO object) {
				return object.getName();
			}
		};
		cellTable.addColumn(nameColumn, "Name");

		TextColumn<OperatorDTO> CPRColumn = new TextColumn<OperatorDTO>(){
			@Override
			public String getValue(OperatorDTO object) {
				return FieldVerifier.cprFormat(object.getCpr());
			}
		};
		cellTable.addColumn(CPRColumn, "CPR number");		

		Column<OperatorDTO, String> editColumn = new Column<OperatorDTO, String>(new ButtonCell(IconType.WRENCH,ButtonType.LINK, ButtonSize.SMALL)){
			@Override
			public String getValue(OperatorDTO object) {
				return "More...";
			}
		};
		cellTable.addColumn(editColumn);

		editColumn.setFieldUpdater(new FieldUpdater<OperatorDTO, String>(){

			@Override
			public void update(int index, OperatorDTO object, String value) {
				(OprListView.this).prod.setView(new OprDetail(object));
			}

		});
		dataProvider.setList(opr); 
		dataProvider.addDataDisplay(cellTable);
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		btn_create.addClickHandler((ClickHandler)new CreateClickHandler());
		this.serviceImpl.getOperators(new ListCallback());
	}
	
	private class CreateClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			prod.setView(new CreateOprView(prod));
		}
	}

	private class ListCallback implements AsyncCallback<List <OperatorDTO>>{

		@Override
		public void onFailure(Throwable caught) {
		}
		
		@Override
		public void onSuccess(List<OperatorDTO> result) {
			opr.clear();
			if(!result.isEmpty()){
				for(OperatorDTO d: result){
					opr.add(d);
				}
				dataProvider.refresh();
			}
				
		}
	}
	
	@Override
	public void Update() {
		opr.clear();
		dataProvider.setList(opr);
		this.serviceImpl.getOperators(new ListCallback());
	}

}
