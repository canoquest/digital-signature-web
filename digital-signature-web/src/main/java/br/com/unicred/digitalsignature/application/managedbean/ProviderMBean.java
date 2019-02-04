package br.com.unicred.digitalsignature.application.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.unicred.autentique.client.service.AutentiqueService;
import br.com.unicred.d4sign.client.service.D4SignService;
import br.com.unicred.digitalsignature.application.enumeration.ApplicationEnum;
import br.com.unicred.digitalsignature.application.enumeration.NavigationEnum;
import br.com.unicred.digitalsignature.application.enumeration.ProviderEnum;
import br.com.unicred.digitalsignature.application.enumeration.UrlFinishedEnum;
import br.com.unicred.digitalsignature.application.model.dto.SessionUserDTO;
import br.com.unicred.digitalsignature.application.model.dto.UserDTO;
import br.com.unicred.digitalsignature.core.managedbean.CoreMBean;
import br.com.unicred.docusign.client.service.AuthenticationService;
import br.com.unicred.signnow.client.service.SignNowService;

@SuppressWarnings("deprecation")
@ManagedBean(name = "providerMBean")
@ViewScoped
public class ProviderMBean extends CoreMBean implements Serializable {

	private static final long serialVersionUID = -8871689713176547120L;
	
	private String provider;
	
	private List<String> listSecurityAttributes;	

	public ProviderMBean() {
		super();
	}

	@Override
	public void initializate() {
		listSecurityAttributes = new ArrayList<String>();		
		Boolean loadSignedDocuments = currentUrlValidate(UrlFinishedEnum.SIGNED_DOCUMENT_BY_USER.getUrlFinished());
		if (loadSignedDocuments) {
			loadSecurityAttributes();
		}	
	}
	
	private void loadSecurityAttributes() {
		listSecurityAttributes.add("Possui certificações.");
		listSecurityAttributes.add("Tráfego de informações é criptografado.");
		listSecurityAttributes.add("Oferece diferentes opções de armazenamentos.");
		listSecurityAttributes.add("Possui OAuth e LDAP como métodos de autenticação.");
	}
	
	public String sendDocument(UserDTO userDTO) {
		byte[] fileByteArray = userDTO.getDocument().getContents();	
		String fileName = userDTO.getDocument().getFileName();		
		String fileBase64 = new String(Base64.getEncoder().encode(fileByteArray));
		String email = "carlos.costa@zallpy.com";
		String url = null;
		
		if (provider != null && !provider.isEmpty()) {
			if (provider.equals(ProviderEnum.DOCUSIGN.getValue())) {
				providerDocuSign(fileBase64, fileByteArray, fileName, email);
			} else if (provider.equals(ProviderEnum.D4SIGN.getValue())) {
				url = providerD4Sign(fileBase64, fileByteArray, fileName, email);
			} else if (provider.equals(ProviderEnum.AUTENTIQUE.getValue())) {
				url = providerAutentique(fileBase64, fileByteArray, fileName, email);
			} else if (provider.equals(ProviderEnum.ADOBESIGN.getValue())) {
				
			} else if (provider.equals(ProviderEnum.SIGNNOW.getValue())) {
				url = providerSignNow(fileBase64, fileByteArray, fileName, email);
			}
		}	
		return url;
	}
	
	private String providerD4Sign(String fileBase64, byte[] fileByteArray, String fileName, String email) {
		D4SignService d4SignService = new D4SignService();
		d4SignService.processDocumentSignature(fileBase64, fileByteArray, fileName, email);
		return NavigationEnum.SIGNED_DOCUMENT_BY_USER.getValue();
	}
	
	private String providerAutentique(String fileBase64, byte[] fileByteArray, String fileName, String email) {
		AutentiqueService autentiqueService = new AutentiqueService();
		autentiqueService.processDocumentSignature(fileBase64, fileByteArray, fileName, email);
		return NavigationEnum.SIGNED_DOCUMENT_BY_USER.getValue();
	}
	
	private String providerSignNow(String fileBase64, byte[] fileByteArray, String fileName, String email) {
		SignNowService signNowService = new SignNowService();
		signNowService.processDocumentSignature(fileBase64, fileByteArray, fileName, email);
		return NavigationEnum.SIGNED_DOCUMENT_BY_USER.getValue();
	}
	
	private void providerDocuSign(String fileBase64, byte[] fileByteArray, String fileName, String email) {		
		SessionUserDTO sessionUserDTO = new SessionUserDTO();
		sessionUserDTO.setFileName(fileName);
		sessionUserDTO.setFileBase64(fileBase64);
		sessionUserDTO.setFileByteArray(fileByteArray);
		sessionUserDTO.setUserEmail(email);
		sessionUserDTO.setUserName("");
		
		setSessionAttribute(ApplicationEnum.SESSION_USER.getValue(), sessionUserDTO);
		
		AuthenticationService authenticationService = new AuthenticationService();
		String url = authenticationService.configure();				 
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public List<String> getListSecurityAttributes() {
		return listSecurityAttributes;
	}

	public void setListSecurityAttributes(List<String> listSecurityAttributes) {
		this.listSecurityAttributes = listSecurityAttributes;
	}
	
}