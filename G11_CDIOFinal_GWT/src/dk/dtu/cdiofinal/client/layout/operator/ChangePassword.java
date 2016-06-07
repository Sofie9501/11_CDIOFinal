package dk.dtu.cdiofinal.client.layout.operator;

import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.layout.ProdView;
import dk.dtu.cdiofinal.client.serverconnection.operator.ClientOperatorImpl;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.OperatorDTO;

public class ChangePassword extends AbstractView{

	private static ChangePasswordUiBinder uiBinder = GWT.create(ChangePasswordUiBinder.class);
	private ClientOperatorImpl serviceImpl;
	private ProdView prodView;
	private OperatorDTO opr;
	private final int MIN_LENGTH = 7;
	private final int MAX_LENGTH = 8;

	@UiField
	PasswordTextBox txt_oldPass; 

	@UiField
	PasswordTextBox txt_password;

	@UiField
	PasswordTextBox txt_password_again;

	@UiField
	Modal popup;

	@UiField
	Button btn_save;

	@UiField
	Button btn_ok;

	@UiField
	Heading ok;

	@UiTemplate("changePassword.ui.xml")
	interface ChangePasswordUiBinder extends UiBinder<Widget, ChangePassword> {
	}

	public ChangePassword(ProdView pv) {
		this.prodView = pv;
		this.serviceImpl = new ClientOperatorImpl();
		initWidget(uiBinder.createAndBindUi(this));
		btn_ok.addClickHandler(new OkPopupClickHandler());
		btn_save.addClickHandler(new SaveChangesClickHandler());
		serviceImpl.getOperator(new CallbackGet());
		txt_password_again.addKeyDownHandler((new EnterHandler()));
	}

	private class CallbackGet implements AsyncCallback<OperatorDTO>{

		@Override
		public void onFailure(Throwable caught) {

		}

		@Override
		public void onSuccess(OperatorDTO result) {
			if(result.equals(null)){
				prodView.PreviousView();
			}
			else{
				opr=result;
			}
		}

	}

	private class ChangePasswordCallback implements AsyncCallback<Boolean>{

		@Override
		public void onFailure(Throwable caught) {
			popup.setTitle("Fejl");
			ok.setText("Der er sket en fejl og resultatet er ikke blevet gemt");
			popup.toggle();
		}

		@Override
		public void onSuccess(Boolean result) {
			if (result){
				btn_save.setText(result.toString());
				popup.setTitle("Succes");
				ok.setText("Dine �ndringer er blevet gemt");
				popup.toggle();
			}
			else{
				popup.setTitle("Fejl");
				ok.setText("Der er sket en fejl og resultatet er ikke blevet gemt");
				popup.toggle();
			}

		}

	}

	private class SaveChangesClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			if (saveChange()){
				if(FieldVerifier.passwordValid(txt_password.getText())){
					opr.setPassword(txt_password.getText());
					txt_password.setText(opr.getPassword());
					serviceImpl.updateOperator(opr, new ChangePasswordCallback());
				}
				else{
					popup.setTitle("Fejl");
					ok.setText("Password lever ikke op til reglerne");
					popup.toggle();
				}
			}
			else{
				popup.setTitle("Fejl");
				ok.setText("Password lever ikke op til reglerne");
				popup.toggle();
			}
		}
	}

	private class EnterHandler implements KeyDownHandler {
		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
				if(saveChange()){
					if(FieldVerifier.passwordValid(txt_password.getText())){
						opr.setPassword(txt_password.getText());
						txt_password.setText(opr.getPassword());
						serviceImpl.updateOperator(opr, new ChangePasswordCallback());
					}
					else{
						popup.setTitle("Fejl");
						ok.setText("Password lever ikke op til reglerne");
						popup.toggle();
					}
				}
				else{
					popup.setTitle("Fejl");
					ok.setText("Password lever ikke op til reglerne");
					popup.toggle();
				}
			}
		}
	}


	private boolean saveChange(){
		return (opr.getPassword().equals(txt_oldPass.getText()) && txt_password.getText().equals(txt_password_again.getText()) && txt_password.getText().length() >= MIN_LENGTH && txt_password.getText().length() <= MAX_LENGTH && !txt_oldPass.getText().equals(txt_password.getText()));
	}

	private class OkPopupClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			popup.toggle();
			prodView.PreviousView();
		}
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}
}
