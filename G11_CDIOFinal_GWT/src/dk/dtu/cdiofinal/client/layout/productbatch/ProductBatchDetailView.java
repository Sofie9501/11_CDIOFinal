package dk.dtu.cdiofinal.client.layout.productbatch;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.serverconnection.productbatch.ClientProductBatchImpl;
import dk.dtu.cdiofinal.shared.ProductBatchDTO;

public class ProductBatchDetailView extends AbstractView {

	private static ProductBatchDetailViewUiBinder uiBinder = GWT.create(ProductBatchDetailViewUiBinder.class);

	@UiTemplate("ProductBatchDetailView.ui.xml")
	interface ProductBatchDetailViewUiBinder extends UiBinder<Widget, ProductBatchDetailView> {
	}
	
	private ProductBatchDTO batch;
	private ClientProductBatchImpl serviceImpl;

	public ProductBatchDetailView(ProductBatchDTO object) {
		this.
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

}
