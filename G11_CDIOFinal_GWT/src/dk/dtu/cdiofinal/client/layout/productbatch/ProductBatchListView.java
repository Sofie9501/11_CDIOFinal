package dk.dtu.cdiofinal.client.layout.productbatch;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProductBatchListView extends Composite {

	private static ProductBatchListViewUiBinder uiBinder = GWT.create(ProductBatchListViewUiBinder.class);

	interface ProductBatchListViewUiBinder extends UiBinder<Widget, ProductBatchListView> {
	}

	public ProductBatchListView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
