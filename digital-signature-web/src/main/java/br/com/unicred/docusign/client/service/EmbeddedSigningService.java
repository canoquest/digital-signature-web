package br.com.unicred.docusign.client.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.model.Document;
import com.docusign.esign.model.Envelope;
import com.docusign.esign.model.EnvelopeDefinition;
import com.docusign.esign.model.EnvelopeDocumentsResult;
import com.docusign.esign.model.EnvelopeSummary;
import com.docusign.esign.model.RecipientViewRequest;
import com.docusign.esign.model.Recipients;
import com.docusign.esign.model.SignHere;
import com.docusign.esign.model.Signer;
import com.docusign.esign.model.Tabs;
import com.docusign.esign.model.ViewUrl;

public class EmbeddedSigningService {	
	
	public EmbeddedSigningService() {
		super();
	}
	
	public String accessEmbeddedSigning(String token, String fileBase64, byte[] fileByteArray, String fileName, String email) throws ApiException {
		 // Data for this example
        // Fill in these constants
        //
        // Obtain an OAuth access token from https://developers.hqtest.tst/oauth-token-generator
        String accessToken = token;
        // Obtain your accountId from demo.docusign.com -- the account id is shown in the drop down on the
        // upper right corner of the screen by your picture or the default picture.
        String accountId = "7764809";
        // Recipient Information
        String signerName = "Carlos Costa";
//        String signerEmail = "carlos.costa@zallpy.com";
        String signerEmail = email;

        // The url for this web application
        String baseUrl = "http://localhost:8080";
        String clientUserId = "123"; // Used to indicate that the signer will use an embedded
        // Signing Ceremony. Represents the signer's userId within
        // your application.
        String authenticationMethod = "None"; // How is this application authenticating
        // the signer? See the `authenticationMethod' definition
        //  https://developers.docusign.com/esign-rest-api/reference/Envelopes/EnvelopeViews/createRecipient
        //
        // The API base path
        String basePath = "https://demo.docusign.net/restapi";
        // The document to be signed. See /qs-java/src/main/resources/World_Wide_Corp_lorem.pdf
//        String docPdf = "World_Wide_Corp_lorem.pdf";
        String docPdf = "/files/arquivo11.pdf";        
        
        // Step 1. Create the envelope definition
        // One "sign here" tab will be added to the document.

//        byte[] buffer = null;
//        try {
//        	buffer = readFile(docPdf);
//        } catch (IOException ex) {
//        	System.out.println("Erro na leitura do arquivo");
//        }       
//        String docBase64 = new String(Base64.encode(buffer));
        String docBase64 = fileBase64;

        // Create the DocuSign document object
        Document document = new Document();
        document.setDocumentBase64(docBase64);
        document.setName(fileName); // can be different from actual file name
        document.setFileExtension("pdf"); // many different document types are accepted
        document.setDocumentId("1"); // a label used to reference the doc

        // The signer object
        // Create a signer recipient to sign the document, identified by name and email
        // We set the clientUserId to enable embedded signing for the recipient
        Signer signer = new Signer();
        signer.setEmail(signerEmail);
        signer.setName(signerName);
        signer.clientUserId(clientUserId);
        signer.recipientId("1");

        // Create a signHere tabs (also known as a field) on the document,
        // We're using x/y positioning. Anchor string positioning can also be used
        SignHere signHere = new SignHere();
        signHere.setDocumentId("1");
        signHere.setPageNumber("1");
        signHere.setRecipientId("1");
        signHere.setTabLabel("SignHereTab");
        signHere.setXPosition("195");
        signHere.setYPosition("147");

        // Add the tabs to the signer object
        // The Tabs object wants arrays of the different field/tab types
        Tabs signerTabs = new Tabs();
        signerTabs.setSignHereTabs(Arrays.asList(signHere));
        signer.setTabs(signerTabs);

        // Next, create the top level envelope definition and populate it.
        EnvelopeDefinition envelopeDefinition = new EnvelopeDefinition();
        envelopeDefinition.setEmailSubject("Please sign this document");
        envelopeDefinition.setDocuments(Arrays.asList(document));
        // Add the recipient to the envelope object
        Recipients recipients = new Recipients();
        recipients.setSigners(Arrays.asList(signer));
        envelopeDefinition.setRecipients(recipients);
        envelopeDefinition.setStatus("sent"); // requests that the envelope be created and sent.
        
     // Step 2. Call DocuSign to create and send the envelope
        ApiClient apiClient = new ApiClient(basePath);
        apiClient.addDefaultHeader("Authorization", "Bearer " + accessToken);
        EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);
        EnvelopeSummary results = envelopesApi.createEnvelope(accountId, envelopeDefinition);
        String envelopeId = results.getEnvelopeId();

        // Step 3. The envelope has been created.
        // Request a Recipient View URL (the Signing Ceremony URL)
        RecipientViewRequest viewRequest = new RecipientViewRequest();
        // Set the url where you want the recipient to go once they are done signing
        // should typically be a callback route somewhere in your app.
        viewRequest.setReturnUrl(baseUrl + "/digital-signature-web/application/modules/documentSignature/signedDocumentByUser.jsf");
        viewRequest.setAuthenticationMethod(authenticationMethod);
        viewRequest.setEmail(signerEmail);
        viewRequest.setUserName(signerName);
        viewRequest.setClientUserId(clientUserId);
        // call the CreateRecipientView API
        ViewUrl results1 = envelopesApi.createRecipientView(accountId, envelopeId, viewRequest);

        // Step 4. The Recipient View URL (the Signing Ceremony URL) has been received.
        //         The user's browser will be redirected to it.
        String redirectUrl = results1.getUrl();
//        RedirectView redirect = new RedirectView(redirectUrl);
//        redirect.setExposeModelAttributes(false);
//        return redirect;
        
        return redirectUrl;
	}
	
	private byte[] readFile(String filePath) throws IOException {
		URL url = getClass().getClassLoader().getResource(filePath);       
        String path = url.getFile();
        File file = new File(path);  
        byte[] bytesArray = new byte[(int) file.length()];        
        
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray); //read file into bytes[]
        fis.close();       
        
		return bytesArray;
	}	
	
	public void getEnvelope() throws ApiException {
		EnvelopesApi envelopesApi = new EnvelopesApi();
		String accountId = null;
		String envelopeId = null;		
		Envelope envelope = envelopesApi.getEnvelope(accountId, envelopeId);
	}
	
	public void listDocuments() throws ApiException {
		EnvelopesApi envelopesApi = new EnvelopesApi();
		String accountId = null;
		String envelopeId = null;		
		EnvelopeDocumentsResult envelopeDocumentsResult = envelopesApi.listDocuments(accountId, envelopeId);	
	}	
	
	public void getDocument() throws ApiException {
		EnvelopesApi envelopesApi = new EnvelopesApi();
		String accountId = null;
		String envelopeId = null;		
		String documentId = null;
		byte[] byteArray = envelopesApi.getDocument(accountId, envelopeId, documentId);	
	}

//	public static void main(String[] args) throws ApiException {
//		 // Data for this example
//        // Fill in these constants
//        //
//        // Obtain an OAuth access token from https://developers.hqtest.tst/oauth-token-generator
//        String accessToken = "eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0";
//        // Obtain your accountId from demo.docusign.com -- the account id is shown in the drop down on the
//        // upper right corner of the screen by your picture or the default picture.
//        String accountId = "7764809";
//        // Recipient Information
//        String signerName = "Carlos Costa";
//        String signerEmail = "carlos.costa@zallpy.com";
//
//
//        // The url for this web application
//        String baseUrl = "http://localhost:8080";
//        String clientUserId = "123"; // Used to indicate that the signer will use an embedded
//        // Signing Ceremony. Represents the signer's userId within
//        // your application.
//        String authenticationMethod = "None"; // How is this application authenticating
//        // the signer? See the `authenticationMethod' definition
//        //  https://developers.docusign.com/esign-rest-api/reference/Envelopes/EnvelopeViews/createRecipient
//        //
//        // The API base path
//        String basePath = "https://demo.docusign.net/restapi";
//        // The document to be signed. See /qs-java/src/main/resources/World_Wide_Corp_lorem.pdf
//        String docPdf = "World_Wide_Corp_lorem.pdf";
//        
//        // Step 1. Create the envelope definition
//        // One "sign here" tab will be added to the document.
//
//        byte[] buffer = readFile(docPdf);
//        String docBase64 = new String(Base64.encode(buffer));
//
//        // Create the DocuSign document object
//        Document document = new Document();
//        document.setDocumentBase64(docBase64);
//        document.setName("Example document"); // can be different from actual file name
//        document.setFileExtension("pdf"); // many different document types are accepted
//        document.setDocumentId("1"); // a label used to reference the doc
//
//        // The signer object
//        // Create a signer recipient to sign the document, identified by name and email
//        // We set the clientUserId to enable embedded signing for the recipient
//        Signer signer = new Signer();
//        signer.setEmail(signerEmail);
//        signer.setName(signerName);
//        signer.clientUserId(clientUserId);
//        signer.recipientId("1");
//
//        // Create a signHere tabs (also known as a field) on the document,
//        // We're using x/y positioning. Anchor string positioning can also be used
//        SignHere signHere = new SignHere();
//        signHere.setDocumentId("1");
//        signHere.setPageNumber("1");
//        signHere.setRecipientId("1");
//        signHere.setTabLabel("SignHereTab");
//        signHere.setXPosition("195");
//        signHere.setYPosition("147");
//
//        // Add the tabs to the signer object
//        // The Tabs object wants arrays of the different field/tab types
//        Tabs signerTabs = new Tabs();
//        signerTabs.setSignHereTabs(Arrays.asList(signHere));
//        signer.setTabs(signerTabs);
//
//        // Next, create the top level envelope definition and populate it.
//        EnvelopeDefinition envelopeDefinition = new EnvelopeDefinition();
//        envelopeDefinition.setEmailSubject("Please sign this document");
//        envelopeDefinition.setDocuments(Arrays.asList(document));
//        // Add the recipient to the envelope object
//        Recipients recipients = new Recipients();
//        recipients.setSigners(Arrays.asList(signer));
//        envelopeDefinition.setRecipients(recipients);
//        envelopeDefinition.setStatus("sent"); // requests that the envelope be created and sent.
//        
//     // Step 2. Call DocuSign to create and send the envelope
//        ApiClient apiClient = new ApiClient(basePath);
//        apiClient.addDefaultHeader("Authorization", "Bearer " + accessToken);
//        EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);
//        EnvelopeSummary results = envelopesApi.createEnvelope(accountId, envelopeDefinition);
//        String envelopeId = results.getEnvelopeId();
//
//        // Step 3. The envelope has been created.
//        //         Request a Recipient View URL (the Signing Ceremony URL)
//        RecipientViewRequest viewRequest = new RecipientViewRequest();
//        // Set the url where you want the recipient to go once they are done signing
//        // should typically be a callback route somewhere in your app.
//        viewRequest.setReturnUrl(baseUrl + "/ds-return");
//        viewRequest.setAuthenticationMethod(authenticationMethod);
//        viewRequest.setEmail(signerEmail);
//        viewRequest.setUserName(signerName);
//        viewRequest.setClientUserId(clientUserId);
//        // call the CreateRecipientView API
//        ViewUrl results1 = envelopesApi.createRecipientView(accountId, envelopeId, viewRequest);
//
//        // Step 4. The Recipient View URL (the Signing Ceremony URL) has been received.
//        //         The user's browser will be redirected to it.
//        String redirectUrl = results1.getUrl();
////        RedirectView redirect = new RedirectView(redirectUrl);
////        redirect.setExposeModelAttributes(false);
////        return redirect;
//
//	}

		

}