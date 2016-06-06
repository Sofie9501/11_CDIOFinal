package dk.dtu.cdiofinal.client.layout;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.layout.operator.ChangePassword;
import dk.dtu.cdiofinal.client.layout.operator.OprListView;
import dk.dtu.cdiofinal.client.serverconnection.operator.ServiceClientOperatorImpl;

public class AdminMenu extends AbstractView{
	private static AdminMenuUiBinder uiBinder = GWT.create(AdminMenuUiBinder.class);
	private ServiceClientOperatorImpl serviceImpl;
	
	private ProdView prodView;
	
	
	
	@UiTemplate("adminmenu.ui.xml")
	interface AdminMenuUiBinder extends UiBinder<Widget, AdminMenu> {
	}
	
	@UiField
	Button btn_opr;
	
	@UiField
	Button btn_myDetails;
	
	
	public AdminMenu(ProdView v) {
		this.prodView = v;
		this.serviceImpl = new ServiceClientOperatorImpl();
		
		// Check if logged in
		// v.changeToMenu()
		// else
		initWidget(uiBinder.createAndBindUi(this));
		
		// hidden until login verified
		btn_opr.setVisible(false);
		btn_opr.addClickHandler(new OprClickHandler());
		btn_myDetails.addClickHandler(new DetailsClickHandler());
		serviceImpl.isLoggedIn(new LoggedInCallback());
				
	}
	
	
	private class OprClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			prodView.setView(new OprListView(prodView));
		}	
	}
	
	private class DetailsClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			prodView.setView(new ChangePassword(prodView));
		}
	}
	
	private class LoggedInCallback implements AsyncCallback<Integer>{

		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Integer result) {
			if(result == 0){
				prodView.logout();
			}
			
			// No breaks. we want  
			switch(result){
			case 1:
				btn_opr.setVisible(true);
			case 2:
				
			case 3:
				break;
				default:
			}
			
			
		}
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
}
