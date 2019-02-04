package br.com.unicred.digitalsignature.application.managedbean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.docusign.esign.client.ApiException;

import br.com.unicred.digitalsignature.application.enumeration.ApplicationEnum;
import br.com.unicred.digitalsignature.application.model.dto.SessionUserDTO;
import br.com.unicred.digitalsignature.core.managedbean.CoreMBean;
import br.com.unicred.docusign.client.service.AuthenticationService;
import br.com.unicred.docusign.client.service.EmbeddedSigningService;

@SuppressWarnings("deprecation")
@ManagedBean(name = "docuSignMBean")
@ViewScoped
public class DocuSignMBean extends CoreMBean implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	private String code;
	
	private Boolean panelShow;

	public DocuSignMBean() {
		super();
	}

	@Override
	public void initializate() {		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		code = request.getParameter("code");	
		documentSign();
	}
	
	public void documentSign() {
		SessionUserDTO sessionUserDTO = (SessionUserDTO) getAndRemoveSessionAttribute(ApplicationEnum.SESSION_USER.getValue());		
		
		String fileName = sessionUserDTO.getFileName();
		byte[] fileByteArray = sessionUserDTO.getFileByteArray();				
		String fileBase64 = sessionUserDTO.getFileBase64();
		String email = sessionUserDTO.getUserEmail();		
		
		try {
			AuthenticationService authenticationService = new AuthenticationService();
			String token = authenticationService.authenticate(code);
			
			EmbeddedSigningService embeddedService = new EmbeddedSigningService();
			String redirectUrl = embeddedService.accessEmbeddedSigning(token, fileBase64, fileByteArray, fileName, email);
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(redirectUrl);		
		} catch (IllegalArgumentException ex) {			
			
		} catch (ApiException ex) {
			
		} catch (IOException ex) {
			
		}
	}

	public Boolean getPanelShow() {
		return panelShow;
	}

	public void setPanelShow(Boolean panelShow) {
		this.panelShow = panelShow;
	}
	
}