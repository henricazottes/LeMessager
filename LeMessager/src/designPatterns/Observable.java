package designPatterns;

import java.util.ArrayList;
import java.util.Iterator;

public class Observable {
	private ArrayList<Observer> obs;
	
	public void addObserver(Observer o){
		this.obs.add(o);
	}
	
	public void removeObserver(Observer o){
		this.obs.remove(o);
	}
	
	public void notifyObs(){
		Iterator<Observer> it_obs = this.obs.iterator();
		while(it_obs.hasNext()){
			((Observer) it_obs).update();
		}
	};	
}
