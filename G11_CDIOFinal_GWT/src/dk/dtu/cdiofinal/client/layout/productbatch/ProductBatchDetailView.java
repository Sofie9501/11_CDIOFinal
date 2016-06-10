package dk.dtu.cdiofinal.client.layout.productbatch;

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
import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.serverconnection.productbatch.ClientProductBatchImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.ProductBatchDTO;

public class ProductBatchDetailView extends AbstractView {

	private static ProductBatchDetailViewUiBinder uiBinder = GWT.create(ProductBatchDetailViewUiBinder.class);

	@UiTemplate("productBatchDetailView.ui.xml")
	interface ProductBatchDetailViewUiBinder extends UiBinder<Widget, ProductBatchDetailView> {
	}
	
	private ProductBatchDTO batch;
	private ClientProductBatchImpl serviceImpl;
	private int oldID;
	
	@UiField
	Heading txt_prodBatchID;
	@UiField
	Heading txt_resipeID;
	@UiField
	Heading txt_status;
	@UiField
	Heading txt_active;
	@UiField
	Heading txt_recipeName;
	@UiField
	Heading txt_countFinished;
	@UiField
	Heading txt_countComponents;
	@UiField
	Heading txt_startDate;
	@UiField
	Heading txt_endDate;
	
	@UiField
	Button btn_prodBatchID;
	@UiField
	Button btn_resipeID;
	@UiField
	Button btn_active;
	
	@UiField
	Modal popup;
	@UiField
	TextBox txt_edited;
	@UiField
	Button btn_save;

	public ProductBatchDetailView(ProductBatchDTO object) {
		this.batch = object;
		oldID = object.getPb_ID();
		initWidget(uiBinder.createAndBindUi(this));
		this.serviceImpl = new ClientProductBatchImpl();
		
		txt_prodBatchID.setText(String.valueOf(object.getPb_ID()));
		txt_resipeID.setText(String.valueOf(object.getR_ID()));
		txt_status.setText(String.valueOf(object.getStatus()));
		txt_active.setText(String.valueOf(object.isActive()));
		txt_recipeName.setText(object.getName());
		txt_countFinished.setText(String.valueOf(object.getCountFinished()));
		txt_countComponents.setText(String.valueOf(object.getCountComponents()));
		txt_startDate.setText(String.valueOf(object.getStart_date()));
		txt_endDate.setText(String.valueOf(object.getEnd_date()));
		
		btn_prodBatchID.addClickHandler((ClickHandler)new EditProdIDHandler());
		btn_resipeID.addClickHandler((ClickHandler) new EditResipeIDHandler());
		btn_active.addClickHandler((ClickHandler) new EditActiveHandler());
		btn_save.addClickHandler((ClickHandler) new SaveClickHandler());
		txt_edited.addKeyDownHandler((KeyDownHandler) new EnterHandler());
	}
	
	// Makes it possible to hit ENTER instead of the Save button.
	private class EnterHandler implements KeyDownHandler {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
				saveChanges();
			}		
		}	
	}
	//Click handlers for all the different buttons.
		private class EditProdIDHandler implements ClickHandler{
			@Override
			public void onClick(ClickEvent event) {
				popup.setTitle("Change product batch ID");
				popup.setId("Product ID");
				txt_edited.setText(String.valueOf(batch.getPb_ID()));
				popup.toggle();		
			}	
		}
		private class EditResipeIDHandler implements ClickHandler{
			@Override
			public void onClick(ClickEvent event) {
				popup.setTitle("Change recipe ID");
				popup.setId("Resipe ID");
				txt_edited.setText(String.valueOf(batch.getR_ID()));
				popup.toggle();		
			}	
		}
	
		private class EditActiveHandler implements ClickHandler{
			@Override
			public void onClick(ClickEvent event) {
				popup.setTitle("Change active");
				popup.setId("Active");
				txt_edited.setText(String.valueOf(batch.isActive()));
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
		
		private void saveChanges(){
			switch(popup.getId()){
			case "Product ID":
				if (FieldVerifier.numberValid(Integer.parseInt(txt_edited.getText()))){
					batch.setPb_ID(Integer.parseInt((txt_edited.getText())));
					txt_prodBatchID.setText(String.valueOf(batch.getPb_ID()));
				}
				else{
					Window.alert("Error - Wrong input");
				}
				break;
			case "Resipe ID":
				if (FieldVerifier.numberValid(Integer.parseInt(txt_edited.getText()))){
					batch.setR_ID(Integer.parseInt((txt_edited.getText())));
					txt_resipeID.setText(String.valueOf(batch.getR_ID()));
				}
				else{
					Window.alert("Error - Wrong input");
				}
				break;
			
			case "active":
				if(txt_edited.getText().equals(true)||txt_edited.getText().equals(false)){
					batch.setActive(Boolean.parseBoolean(txt_edited.getText()));
					txt_active.setText(String.valueOf(batch.isActive()));	
				}
				else{
					Window.alert("Error - Active input not valid");
				}
				break;
			}
			
			serviceImpl.updateProductBatch(batch, oldID, new MyCallback());
		}
		
		private class MyCallback implements AsyncCallback<Boolean>{

			@Override
			public void onFailure(Throwable caught) {
				popup.setTitle("No conection to server");
				
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

	}

