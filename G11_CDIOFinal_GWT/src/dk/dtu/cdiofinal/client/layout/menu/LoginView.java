package dk.dtu.cdiofinal.client.layout.menu;

import com.github.gwtbootstrap.client.ui.*;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import dk.dtu.cdiofinal.client.MainView;
import dk.dtu.cdiofinal.client.serverconnection.ClientMenuImpl;

public class LoginView extends Composite{
	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	private ClientMenuImpl serviceImpl;

	@UiTemplate("login.ui.xml")
	interface LoginUiBinder extends UiBinder<Widget, LoginView> {
	}


	@UiField
	TextBox txt_username; 

	@UiField
	PasswordTextBox txt_password;

	@UiField
	Button btn_login;

	@UiField
	Button btn_loginFail;

	@UiField
	Modal popup_login;

	MainView mainView;




	public LoginView(MainView v) {
		this.mainView = v;
		this.serviceImpl = new ClientMenuImpl();

		// Check if logged in
		// v.changeToMenu()
		// else

		initWidget(uiBinder.createAndBindUi(this));
		btn_login.addClickHandler(new LoginClickHandler());
		txt_password.addKeyDownHandler(new EnterHandler());
		btn_loginFail.addClickHandler((ClickHandler) (new OkClickHandler()));		
	}
	private class EnterHandler implements KeyDownHandler {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
				serviceImpl.login(Integer.parseInt(txt_username.getText()), txt_password.getText(), new LoginCallback());
			}		
		}	
	}
	private class LoginClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			serviceImpl.login(Integer.parseInt(txt_username.getText()), txt_password.getText(), new LoginCallback());
		}
	}

	private class LoginCallback implements AsyncCallback<Boolean>{

		@Override
		public void onFailure(Throwable caught) {

		}

		@Override
		public void onSuccess(Boolean result) {
			if(result){
				mainView.changeToProd();
			}
			else{
				popup_login.setTitle("Der er fejl i Operatør ID eller Password");
				popup_login.toggle();
			}

		}


	}
	
	private class OkClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			popup_login.toggle();

		}
	}
}
