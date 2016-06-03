package dk.dtu.cdiofinal.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import dk.dtu.cdio3.client.layout.Login;
import dk.dtu.cdio3.client.layout.ProdView;
import dk.dtu.cdio3.shared.OperatoerDTO;



public class MainView extends Composite{
	

	OperatoerDTO opr = new OperatoerDTO(12, "Cille123", 3, "0102003-2233", "loldppspa");

	private VerticalPanel vPanel =  new VerticalPanel();
	

	public MainView(){
		initWidget(vPanel);
		this.vPanel.setWidth("100%");
		this.vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		vPanel.add(new Login(this));

	}
	
	public void checkLogin(){
		// if logged in go to admin menu
		//vPanel.add(new MainMenu());
		// else show login screen


		vPanel.add(new ProdView(this));

	}
	
	public void changeToLogin(){
		vPanel.clear();
		vPanel.add(new Login(this));
	}
	
	public void changeToProd(){
		// Check if logged in
		vPanel.clear();
		vPanel.add(new ProdView(this));
	}

	
	
}