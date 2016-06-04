package dk.dtu.cdiofinal.client.helpers;

import dk.dtu.cdiofinal.client.AbstractView;
import dk.dtu.cdiofinal.client.helpers.Element;

public class Stack {
	
private Element head;
	
	public void push(AbstractView c){
		Element e = new Element(head, c);
		this.head = e;
	}
	
	public AbstractView pop(){
		Element temp = head;
		head = head.getNext();
		return temp.getData();
	}
	
	public boolean isEmpty(){
		if(head == null){
			return true;
		}
		return false;
	}

}
