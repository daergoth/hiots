package net.daergoth.net;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "indexManager")
@ViewScoped
public class IndexManager {

	

	@PostConstruct
	public void init() {
		
	}

	private String hello = "Hello!";

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}



}
