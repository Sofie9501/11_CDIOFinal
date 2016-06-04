package dk.dtu.cdiofinal.client.layout;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import dk.dtu.cdiofinal.client.serverconnection.ServiceClientImpl;

public class Menu extends Composite{

	@UiTemplate("menu.ui.xml")
	interface MyUiBinder extends UiBinder<Widget, Menu> {
	}
	
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	private ServiceClientImpl service = new ServiceClientImpl();
	@UiField
	Button back;
	@UiField
	Button account;  
	@UiField
	Button log_out;
	@UiField
	Button menu;
	ProdView v;	  

	public Menu(ProdView v) {
		// sets listBox
		initWidget(uiBinder.createAndBindUi(this));
		this.v = v;
		back.addClickHandler(new BackClickHandler());
		menu.addClickHandler(new MenuClickHandler());
		log_out.addClickHandler(new LogOutClickHandler());
		account.addClickHandler(new AccountClickHandler());
	}

	// tell parent view to move back one level
	private class BackClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			v.PreviousView();

		} 
	}


	// Go to account / opr details for current opr
	private class AccountClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			v.setView(new ChangePassword(v));
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
}
