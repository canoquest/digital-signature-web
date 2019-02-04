package br.com.unicred.digitalsignature.application.managedbean;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.unicred.digitalsignature.application.enumeration.NavigationEnum;
import br.com.unicred.digitalsignature.application.model.dto.UserDTO;
import br.com.unicred.digitalsignature.core.managedbean.CoreMBean;

@Named(value = "digitalSignatureProccessMBean")
@ConversationScoped
public class DigitalSignatureProccessMBean extends CoreMBean implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DigitalSignatureProccessMBean.class);
	
	@Inject
	private Conversation conversation;
	
	private UserDTO userDTO;	
	
	@Inject
	private ProviderMBean providerMBean;

	public DigitalSignatureProccessMBean() {
		super();		
	}
	
	public void initializate() {
		if (userDTO == null) {
			createUser();
		}		
	}

	private void createUser() {
		userDTO = new UserDTO();
	}
	
	public void beginConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	public void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}		
	}
	
	public void validateConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		} else if (!conversation.isTransient()) {
			conversation.end();
			conversation.begin();
		}
	}
	
	public void handleClick() {				
		LOGGER.info("Item Selecionado: " + providerMBean.getProvider());		
	}
	
	public String newDocument() {
		beginConversation();
		return NavigationEnum.NEW_DOCUMENTO.getValue();
	}
	
	public String viewUserValues() {		
		return NavigationEnum.VIEW_USER_VALUES.getValue();
	}
	
	public String selectProvider() {
		return NavigationEnum.SELECT_PROVIDER.getValue();
	}
	
	public String listSignedDocuments() {
		endConversation();
		return NavigationEnum.LIST_SIGNED_DOCUMENTS.getValue();
	}
	
	public String signDocument() {	
		return providerMBean.sendDocument(userDTO);		
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile file = event.getFile();				
		byte [] arquivo = file.getContents();
		
		LOGGER.info("Arquivo: " + arquivo.length);
		LOGGER.info("Arquivo: " + file.getFileName());
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public ProviderMBean getProviderMBean() {
		return providerMBean;
	}

	public void setProviderMBean(ProviderMBean providerMBean) {
		this.providerMBean = providerMBean;
	}

}