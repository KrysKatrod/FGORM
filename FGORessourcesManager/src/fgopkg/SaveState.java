package fgopkg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SaveState implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Servant> data;
	private List<Ressource> data_rsc;
	private List<Servant> data_na;
	private List<Ressource> data_rsc_na;
	
	public SaveState() {
		this.data = new ArrayList<>();
		this.data_rsc= new ArrayList<>();
		this.data_na = new ArrayList<>();
		this.data_rsc_na = new ArrayList<>();
	}
	
	public SaveState(List<Servant> data, List<Ressource> data_rsc, List<Servant> data_na,
			List<Ressource> data_rsc_na) {
		this.data = new ArrayList<>();
		this.data_rsc= new ArrayList<>();
		this.data_na = new ArrayList<>();
		this.data_rsc_na = new ArrayList<>();
		this.data.addAll(data);
		this.data_rsc.addAll(data_rsc);
		this.data_na.addAll(data_na);
		this.data_rsc_na.addAll(data_rsc_na);
	}

	public List<Servant> getData() {
		return data;
	}

	public void setData(List<Servant> data) {
		this.data = data;
	}

	public List<Ressource> getData_rsc() {
		return data_rsc;
	}

	public void setData_rsc(List<Ressource> data_rsc) {
		this.data_rsc = data_rsc;
	}

	public List<Servant> getData_na() {
		return data_na;
	}

	public void setData_na(List<Servant> data_na) {
		this.data_na = data_na;
	}

	public List<Ressource> getData_rsc_na() {
		return data_rsc_na;
	}

	public void setData_rsc_na(List<Ressource> data_rsc_na) {
		this.data_rsc_na = data_rsc_na;
	}
}
