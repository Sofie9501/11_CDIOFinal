package dk.dtu.cdiofinal.client.layout.productbatch;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProductBatchDetailView extends Composite {

	private static ProductBatchDetailViewUiBinder uiBinder = GWT.create(ProductBatchDetailViewUiBinder.class);

	interface ProductBatchDetailViewUiBinder extends UiBinder<Widget, ProductBatchDetailView> {
	}

	public ProductBatchDetailView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
