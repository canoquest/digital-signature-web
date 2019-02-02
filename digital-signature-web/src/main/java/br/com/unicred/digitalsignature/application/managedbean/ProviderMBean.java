package br.com.unicred.digitalsignature.application.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.unicred.api.client.service.APIService;
import br.com.unicred.digitalsignature.application.enumeration.UrlFinishedEnum;
import br.com.unicred.digitalsignature.application.model.dto.UserDTO;
import br.com.unicred.digitalsignature.core.managedbean.CoreMBean;

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
		listSecurityAttributes.add("Possui certifica��es.");
		listSecurityAttributes.add("Tr�fego de informa��es � criptografado.");
		listSecurityAttributes.add("Oferece diferentes op��es de armazenamentos.");
		listSecurityAttributes.add("Possui OAuth e LDAP como m�todos de autentica��o.");
	}
	
	public void sendDocument(UserDTO userDTO) {
		byte[] file = userDTO.getDocument().getContents();	
		String fileName = userDTO.getDocument().getFileName();		
		String documentBase64 = new String(Base64.getEncoder().encode(file));
		
		APIService documentService = new APIService();		
		documentService.sendDocument(documentBase64, file, fileName);
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