package dk.dtu.cdiofinal.client.layout.Menu;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Heading;
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

	@UiTemplate("menu.ui.xml")
	interface MyUiBinder extends UiBinder<Widget, MenuView> {
	}
	
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	private ClientMenuImpl service = new ClientMenuImpl();
	String oprName = null;
	@UiField
	
	Button back;
	@UiField
	Button log_out;
	@UiField
	Button menu;
	@UiField
	Button name;
	ProdView v;	  

	public MenuView(ProdView v) {
		// sets listBox
		initWidget(uiBinder.createAndBindUi(this));
		this.v = v;
		back.addClickHandler(new BackClickHandler());
		menu.addClickHandler(new MenuClickHandler());
		log_out.addClickHandler(new LogOutClickHandler());
		service.loggedInName(new NameCallback());
		
	}

	// tell parent view to move back one level
	private class BackClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			v.PreviousView();

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
			v.mainMenu();

		}

	}
	

	private class LogoutCallback implements AsyncCallback<Boolean>{

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Boolean result) {
			if(result){
				v.logout();
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
