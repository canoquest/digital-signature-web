package br.com.unicred.digitalsignature.core.managedbean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public abstract class CoreMBean {

	public CoreMBean() {
		super();
		initializate();
	}
	
	public abstract void initializate();
	
	public String getCurrentUrl() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String url = "";
		if (request != null && request.getRequestURL() != null) {
			url = request.getRequestURL().toString();
		}
		return url;
	}
	
	public Boolean currentUrlValidate(String urlFinished) {
		Boolean equalUrlFinished = Boolean.FALSE;
		String url = getCurrentUrl();
		if (url != null && urlFinished != null && url.endsWith(urlFinished)) {
			equalUrlFinished = Boolean.TRUE;
		}
		return equalUrlFinished;
	}

}