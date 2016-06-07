package dk.dtu.cdiofinal.client.layout;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.layout.ingredient.IngredientListView;
import dk.dtu.cdiofinal.client.layout.ingredientbatch.IngredientBatchListView;
import dk.dtu.cdiofinal.client.layout.operator.OprListView;
import dk.dtu.cdiofinal.client.layout.recipe.RecipeListView;
import dk.dtu.cdiofinal.client.serverconnection.ClientMenuImpl;

public class AdminMenu extends AbstractView{
	private static AdminMenuUiBinder uiBinder = GWT.create(AdminMenuUiBinder.class);
	private ClientMenuImpl serviceImpl;
	
	private ProdView prodView;
	
	
	
	@UiTemplate("adminmenu.ui.xml")
	interface AdminMenuUiBinder extends UiBinder<Widget, AdminMenu> {
	}
	
	@UiField Button btn_opr;
	@UiField Button btn_recipe;
	@UiField Button btn_ingredient;
	@UiField Button btn_productbatch;
	@UiField Button btn_ingredientbatch;
	
	@UiField
	Alert alert_opr;
	
	
	public AdminMenu(ProdView v) {
		this.prodView = v;
		this.serviceImpl = new ClientMenuImpl();
		
		// Check if logged in
		// v.changeToMenu()
		// else
		initWidget(uiBinder.createAndBindUi(this));
		
		// hidden until login verified
		alert_opr.setVisible(false);
		btn_opr.setVisible(false);
		btn_recipe.setVisible(false);
		btn_ingredient.setVisible(false);
		btn_productbatch.setVisible(false);
		btn_ingredientbatch.setVisible(false);
		btn_opr.addClickHandler(new MenuClickHandler(new OprListView(prodView)));
		btn_recipe.addClickHandler(new MenuClickHandler(new RecipeListView(prodView)));
		btn_ingredientbatch.addClickHandler(new MenuClickHandler(new IngredientBatchListView(prodView)));
		btn_ingredient.addClickHandler(new MenuClickHandler(new IngredientListView(prodView)));
		//btn_productbatch.addClickHandler(new MenuClickHandler(new ProductBatchListView(prodView)))
		serviceImpl.isLoggedIn(new LoggedInCallback());
				
	}
	
	
	
	private class MenuClickHandler implements ClickHandler{
		AbstractView view;
		public MenuClickHandler(AbstractView view){
			this.view=view;
		}
		@Override
		public void onClick(ClickEvent event) {
			prodView.setView(view);
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
			case 4:
				alert_opr.setVisible(true);
				break;
				
			case 1:
				btn_opr.setVisible(true);
				
			case 2:
				btn_recipe.setVisible(true);
				btn_ingredient.setVisible(true);
				btn_ingredientbatch.setVisible(true);
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
