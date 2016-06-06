package dk.dtu.cdiofinal.client.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.MainView;
import dk.dtu.cdiofinal.client.helpers.Stack;
import dk.dtu.cdiofinal.client.serverconnection.operator.ServiceClientOperatorImpl;

public class ProdView extends Composite{
	
	private ServiceClientOperatorImpl service = new ServiceClientOperatorImpl();
	private static ProdViewUiBinder uiBinder = GWT.create(ProdViewUiBinder.class);
	@UiTemplate("prod.ui.xml")
	interface ProdViewUiBinder extends UiBinder<Widget, ProdView> {
	}


	@UiField
	VerticalPanel menuPanel;
	@UiField
	VerticalPanel content;
	private MainView mainView; 
	private Stack viewStack = new Stack();

	public ProdView(MainView v){

		initWidget(uiBinder.createAndBindUi(this));
		this.menuPanel.setWidth("100%");
		this.content.setWidth("100%");
		this.content.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);


		this.menuPanel.add(new Menu(this)); 
		this.content.add(new AdminMenu(this));
		this.mainView = v;
		service.isLoggedIn(new LoggedInCallback());
	}


	public void logout(){

		//Log ud fra server mangler
		mainView.changeToLogin();
	}

	public void PreviousView(){

		if(!viewStack.isEmpty()){
			content.clear();
			AbstractView v = viewStack.pop();
			v.Update();
			content.add(v);
		}
	}

	public void mainMenu(){
		AbstractView c = null;
		while(!viewStack.isEmpty())
			c = viewStack.pop();
		//PreviousView();
		if(c != null){
			content.clear();
			content.add(c);
		}
	}

	public void setView(AbstractView view){
		viewStack.push((AbstractView) content.getWidget(0));
		content.clear();
		content.add(view);
	}

	private class LoggedInCallback implements AsyncCallback<Integer>{

		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Integer result) {
			if(result == 0){
				return;
			}


		}


	}

}
