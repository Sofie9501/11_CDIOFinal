package dk.dtu.cdiofinal.client.layout.productbatch;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CreateProductBatchView extends Composite {

	private static CreateProductBatchViewUiBinder uiBinder = GWT.create(CreateProductBatchViewUiBinder.class);

	interface CreateProductBatchViewUiBinder extends UiBinder<Widget, CreateProductBatchView> {
	}

	public CreateProductBatchView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
