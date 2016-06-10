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
import dk.dtu.cdiofinal.client.layout.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.productbatch.ClientProductBatchImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.ProductBatchDTO;

public class CreateProductBatchView extends AbstractView {
	
	final ProdView prod;
	private ClientProductBatchImpl serviceImpl;
	private static CreateProductBatchViewUiBinder uiBinder = GWT.create(CreateProductBatchViewUiBinder.class);
	private ProductBatchDTO batch;
	
	@UiTemplate("createProductBatchView.ui.xml")
	interface CreateProductBatchViewUiBinder extends UiBinder<Widget, CreateProductBatchView> {
	}
	
	//Textbox, buttons, heading and modal
		@UiField
		TextBox txt_ProdBatchID;
		@UiField
		TextBox txt_ReceptID;
		@UiField
		Button btn_save;

		@UiField
		Modal popup;
		@UiField
		Button btn_ok;
		@UiField
		Heading ok;

	public CreateProductBatchView(ProdView prod) {
		initWidget(uiBinder.createAndBindUi(this));
		this.prod=prod;
		this.serviceImpl = new ClientProductBatchImpl();
		
		//Add click and key handler to buttons and last textbox
		btn_save.addClickHandler(new SaveClickHandler());
		btn_ok.addClickHandler((ClickHandler)new OkClickHandler());
		txt_ReceptID.addKeyDownHandler(new EnterHandler());
		

	}
	private boolean changeSucces(){
		String alert = "";
		boolean succes = true;
		if(!FieldVerifier.numberValid(Integer.parseInt(txt_ProdBatchID.getText()))){
			alert+="Error - You need to write a valid batch ID \n";
			succes = false;
		}
		if(!FieldVerifier.numberValid(Integer.parseInt(txt_ReceptID.getText()))){
			alert += "Error - ID for Recept is not valid \n";
			succes = false;
		}
		if(!alert.equals(""))
			Window.alert(alert);
		return succes;
	}
	private void saveChanges(){
		// Checks to see if there is no errors
		if(changeSucces()){
			batch = new ProductBatchDTO();
			batch.setPb_ID(Integer.parseInt(txt_ProdBatchID.getText()));
			batch.setR_ID(Integer.parseInt(txt_ReceptID.getText()));
			ok.setText("Your information has been saved");
			//Updates the DB with the new productBatch
			serviceImpl.createProductBatch(batch, new MyCallback());
			
			
		}	

	}
	private class SaveClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			saveChanges();
			prod.PreviousView();
		}	
	}	
	private class OkClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			popup.toggle();
			prod.PreviousView();

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
			ok.setText("No server connection");
			popup.toggle();
		}
		@Override
		public void onSuccess(Boolean result) {
			if(result){
			popup.toggle();
			txt_ProdBatchID.setText("");
			txt_ReceptID.setText("");
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