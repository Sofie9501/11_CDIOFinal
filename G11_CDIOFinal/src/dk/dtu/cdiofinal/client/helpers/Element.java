package dk.dtu.cdiofinal.client.helpers;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.helpers.Element;

public class Element {
	private AbstractView data;
	private Element next;
	
	public Element (Element next, AbstractView data){
		this.next = next;
		this.data = data;
	}

	public AbstractView getData(){
		return data;
	}
	
	public Element getNext(){
		return next;
	}
	
	public void setNext(Element next){
		this.next = next;
	}
}
