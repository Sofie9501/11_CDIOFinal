package dk.dtu.cdiofinal.client.layout.menu;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import dk.dtu.cdiofinal.client.serverconnection.ClientMenuImpl;

public class MenuView extends Composite{
	final ProdView prod;
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	private ClientMenuImpl service = new ClientMenuImpl();
	private String oprName = null;

	@UiTemplate("menu.ui.xml")
	interface MyUiBinder extends UiBinder<Widget, MenuView> {
	}
	
	
	@UiField Button back;
	@UiField Button log_out;
	@UiField Button menu;
	@UiField Button name;
		  

	public MenuView(ProdView v) {
		// sets listBox
		initWidget(uiBinder.createAndBindUi(this));
		this.prod = v;
		back.addClickHandler(new BackClickHandler());
		menu.addClickHandler(new MenuClickHandler());
		log_out.addClickHandler(new LogOutClickHandler());
		service.loggedInName(new NameCallback());
		
	}

	// tell parent view to move back one level
	private class BackClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			prod.PreviousView();
		} 
	}

	// Should tell server to log user out and redirect to login screen
	private class LogOutClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			service.logout(new LogoutCallback());

		} 
	}

	private class MenuClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			prod.mainMenu();
		}
	}
	

	private class LogoutCallback implements AsyncCallback<Boolean>{

		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Boolean result) {
			if(result){
				prod.logout();
			}
			else{
				Window.alert("Logout failed, try again");
			}
		}
	}
	
	private class NameCallback implements AsyncCallback<String>{
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("ERROR IN NAME");
		}

		@Override
		public void onSuccess(String result) {
			oprName = result;
			name.setText("Logged in as: " +oprName);
		}
	}
}
