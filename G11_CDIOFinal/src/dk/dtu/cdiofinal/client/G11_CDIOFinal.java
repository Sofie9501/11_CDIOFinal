package dk.dtu.cdiofinal.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import dk.dtu.cdiofinal.client.MainView;

public class G11_CDIOFinal implements EntryPoint {

	public void onModuleLoad() {
		
		RootPanel.get("title").add(new Label("CDIO3"));
		RootPanel.get("content").add(new MainView());
	//	RootPanel.get("content").add(new ProdView(null));
	}
}
