package br.com.unicred.docusign.client.service;

import java.io.IOException;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.token.BasicOAuthToken;

import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.Configuration;
import com.docusign.esign.client.auth.AccessTokenListener;
import com.docusign.esign.client.auth.OAuth.UserInfo;

/**
 * Classe respons�vel pelas etapas de autentica��o na API DocuSign. <br>
 * 
 * Todos os c�digos fonte implementados nessa classe, bem como as devidas explica��es sobre como obter 
 * as informa��es de <b>SECRET_KEY</b>, <b>INTEGRATION_KEY</b>, <b>REDIRECT_URI</b>, <b>API_ACCOUNT</b> e 
 * <b>API_USERNAME</b> est�o dispon�veis na URL 
 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> da documenta��o 
 * oficial da API do DocuSign. <br><br>
 * 
 * A autentica��o consiste em quatro etapas: <br>
 * 1�) Configura��o dos par�metros <b>INTEGRATOR KEY</b>, <b>SECRET KEY</b> e <b>REDIRECT URI</b>. 
 * As informa��es para as configura��es dessas propriedades est�o dispon�veis na URL
 * <a href="https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant">
 * https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant</a> na sess�o
 * <b>Prerequisites</b>. <br>
 * 
 * 2�) Consiste em obter o c�digo de acesso. Mais informa��es na URL 
 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> na sess�o 
 * <b>Configure Client and Request Authorization Code</b>. <br>
 * 
 * 3�) Consiste em obter o token. Mais informa��es na URL 
 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> na sess�o
 * <b>Request Authentication Token</b>. <br>
 * 
 * 4�) Consiste em obter a URI base. Mais informa��es na URL 
 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> na sess�o
 * <b>Get base URI</b>. <br> 
 * 
 * <b>Observa��o:</b> <br>
 * Se o valor de qualquer uma das propriedades <b>SERCRET KEY</b>, <b>INTEGRATOR KEY</b> e <b>REDIRECT KEY</b> 
 * for alterado, o mesmo valor precisa ser alterado acessando a URL 
 * <a href="https://admindemo.docusign.com/api-integrator-key">
 * https://admindemo.docusign.com/api-integrator-key</a>.
 * 
 * @author carlos.costa
 * @version 1.0
 * @since 15/01/2015
 */
public class AuthenticationService {

	/** Obtido no Admin do Sandbox. URL <a href="https://admindemo.docusign.com/api-integrator-key">
	 *  https://admindemo.docusign.com/api-integrator-key</a> */
	@SuppressWarnings("unused")
	private static final String API_ACCOUNT_ID = "e937119f-3d7c-4c26-b8f0-371d5a9315c7";
	
	/** Obtido no Admin do Sandbox. URL <a href="https://admindemo.docusign.com/api-integrator-key">
	 *  https://admindemo.docusign.com/api-integrator-key</a> */
	@SuppressWarnings("unused")
	private static final String API_USERNAME = "016ab11c-ca01-40b0-93ae-2a9196dae502";
	
	/** Obtido no Admin do Sandbox. URL <a href="https://admindemo.docusign.com/admin">
	 *  https://admindemo.docusign.com/admin</a> */
	@SuppressWarnings("unused")
	private static final String CLIENT_ID = "7764809";
	
	/** C�digo de acesso obtido ap�s a segunda etapa de autentica��o.
	 *  As informa��es para obter esse c�digo de acesso est�o dispon�evis na URL 
	 *  <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
	 *  https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> na sess�o
	 *  <b>Configure Client and Request Authorization Code</b> 
	 */
	@SuppressWarnings("unused")
	private static final String ACCESS_CODE = "eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQkAAAABAAYABwAAOCN4CXvWSAgAAMSpvwl71kgCAByxagEByrBAk64qkZba5QIVAAEAAAAYAAEAAAAFAAAADQAkAAAAYTViMDZmOTctNGI4OC00ODFjLTkwYmItMjhhZGM5NWQ1YmFlMAAAOCN4CXvWSBIAAQAAAAsAAABpbnRlcmFjdGl2ZQ.JZA6UEv7v80Mbw7rCBW9ICr867YvWswK3Z1-C2Xf3wogpX5ZlS86TxRYU9ejB7Fo9GZlblpK_SLIyhDmuWM7PbEOIHlocKL0SjdqQBa4RsZdqXEvoC1AyaKgbAUO2D-SHxrZZhuv43FWgjwTfreSUj3YiNxA5D8o5tcERujlkFV30IkBNQ_T8k3b_HAOjcsVGW00twcvBNPUA6lGS0dojQnaUgfslQz69TO7pXqekm9_ePhEtaVU2QqNnrKl8lBt1CNV0j1YIMBPZdhX3y0sG0NtAc89UarJSgaKEEmz4pnxHcB77S8NyiCoFVg02yY7lFXs9fv-jKsDLv7005vV1g";
	
	/** Obtido no Admin do Sandbox. URL 
	 *  <a href="https://admindemo.docusign.com/api-integrator-key">
	 *  https://admindemo.docusign.com/api-integrator-key</a> 
	 *  As informa��es para obter a Secret Key est�o dispon�veis na URL 
	 *  <a href="https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant">
	 *  https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant</a> 
	 *  na sess�o <b>Prerequisites</b>
	 */
	private static final String SECRET_KEY = "0da4be6a-a2a6-4529-98fd-5a18007a29e6";
	
	/** Obtido no Admin do Sandbox. URL 
	 *  <a href="https://admindemo.docusign.com/api-integrator-key">
	 *  https://admindemo.docusign.com/api-integrator-key</a> 
	 *  As informa��es para obter a Integrator Key est�o dispon�veis na URL 
	 *  <a href="https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant">
	 *  https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant</a> 
	 *  na sess�o <b>Prerequisites</b>
	 */
	private static final String INTEGRATOR_KEY = "a5b06f97-4b88-481c-90bb-28adc95d5bae";
	
	/** Obtido no Admin do Sandbox. URL 
	 *  <a href="https://admindemo.docusign.com/api-integrator-key">
	 *  https://admindemo.docusign.com/api-integrator-key</a> 
	 *  As informa��es para obter a Redirect URI est�o dispon�veis na URL 
	 *  <a href="https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant">
	 *  https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant</a> 
	 *  na sess�o <b>Prerequisites</b>
	 */
	// http://localhost:8080/docusign-web/application/modules/embedded/embedded.jsp
	private static final String REDIRECT_URI = "http://localhost:8080/digital-signature-web/application/modules/provider/docusign.jsf";	
	
	/** URL informada no c�digo fonte de exemplo dispon�vel em 
	 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
	 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> 
	 */
	private static final String AUTH_SERVER_URL = "https://account-d.docusign.com";
	
	private ApiClient apiClient;
	
	private static ApiClient apiClientStatic;
	
	/**
	 * M�todo construtor padr�o.
	 */
	public AuthenticationService() {
		super();
	}
	
	/**
	 * M�todo respons�vel pela cria��o do objeto {@link com.docusign.esign.client.ApiClient}
	 * 
	 * @return Retorna uma inst�ncia de {@link com.docusign.esign.client.ApiClient}
	 */
	private ApiClient getApiClient() {
		// Java setup and config
		// ** String IntegratorKey = INTEGRATOR_KEY;

		// generate a client secret for the integrator key you supply above, again through sandbox admin menu
		// ** String ClientSecret = SECRET_KEY;		

		// use demo authentication server (remove -d for production)
		// ** String AuthServerUrl = "https://account-d.docusign.com";
		
		// instantiate the api client and point to auth server
		if (apiClientStatic != null) {
			apiClient = apiClientStatic;
		} else {
			if (apiClient == null) {
				apiClient = new ApiClient(AUTH_SERVER_URL, "docusignAccessCode", INTEGRATOR_KEY, SECRET_KEY);
				apiClientStatic = apiClient;
			}		
		}
		
		return apiClient;
	}
	
	/**
	 * M�todo respons�vel por atualizar a propriedade est�tica apiClientStatic.
	 * 
	 * @param apiClient Recebe uma inst�ncia de {@link com.docusign.esign.client.ApiClient}
	 */
	private void setApiClient(ApiClient apiClient) {
		apiClientStatic = apiClient;
	}
	
	/**
	 * Esse m�todo executa o c�digo fonte apresentado na sess�o <b>Configure Client and Request Authorization Code</b>.
	 * Essa sess�o pode ser acessada a partir da URL 
	 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
	 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a>.
	 * M�todo respons�vel pela segunda etapa do processo de autentica��o. Consiste em obter o c�digo de autentica��o.
	 * 
	 * @throws IOException Lan�a a exce��o {@link java.io.IOException}
	 */
	public String configure() {
		// Java setup and config
		// ** String IntegratorKey = INTEGRATOR_KEY;

		// generate a client secret for the integrator key you supply above, again through sandbox admin menu
		// ** String ClientSecret = SECRET_KEY;

		// must match a redirect URI (case-sensitive) you configured on the key
		// ** String RedirectURI = REDIRECT_URI;

		// use demo authentication server (remove -d for production)
		// ** String AuthServerUrl = AUTH_SERVER_URL;

		// point to the demo (sandbox) environment. For production requests your account sub-domain 
		// will vary, you should always use the base URI that is returned from authentication to
		// ensure your integration points to the correct endpoints (in both environments)
		String RestApiUrl = "https://demo.docusign.net/restapi";

		// instantiate the api client and point to auth server
		// ApiClient apiClient = new ApiClient(AuthServerUrl, "docusignAccessCode", IntegratorKey, ClientSecret);
		apiClient = getApiClient();

		// set the base path for REST API requests
		apiClient.setBasePath(RestApiUrl);

		// configure the authorization flow on the api client
		apiClient.configureAuthorizationFlow(INTEGRATOR_KEY, SECRET_KEY, REDIRECT_URI);

		// set as default api client in your configuration
		Configuration.setDefaultApiClient(apiClient);	
		
		
		// get DocuSign OAuth authorization url
		String oauthLoginUrl = null;
		try {
			oauthLoginUrl = apiClient.getAuthorizationUri();
			setApiClient(apiClient);
		} catch (OAuthSystemException e) {			
			e.printStackTrace();
		}
		return oauthLoginUrl;
	}
	
	/**
	 * Esse m�todo executa o c�digo fonte apresentado na sess�o <b>Request Authentication Token</b>.
	 * Essa sess�o pode ser acessada a partir da URL 
	 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
	 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a>.
	 * M�todo respons�vel pela terceira etapa do processo de autentica��o. Consiste em obter o token.
	 * 
	 * @param accessCode - C�digo de acesso obtido na primeira etapa do processo de autentica��o.
	 */
	public void getToken(String accessCode) {
		// Java request auth token
		String code = accessCode;
		
		// use demo authentication server (remove -d for production)
		// ** String AuthServerUrl = "https://account-d.docusign.com";
		
		// instantiate the api client and point to auth server
		apiClient = getApiClient();
		
		// assign it to the token endpoint
		apiClient.getTokenEndPoint().setCode(code);

		// optionally register to get notified when a new token arrives
		apiClient.registerAccessTokenListener(new AccessTokenListener() {
			
		    public void notify(BasicOAuthToken token) {
		        System.out.println("Got a fresh token: " + token.getAccessToken());
		    }
		    
		});
		
		// following call exchanges the authorization code for an access code and updates 
		// the `Authorization: bearer <token>` header on the api client
		apiClient.updateAccessToken();
		System.out.println("Token: " + apiClient.getAccessToken());		
	}
	
	/**
	 * Esse m�todo executa o c�digo fonte apresentado na sess�o <b>Get base URI</b>.
	 * Essa sess�o pode ser acessada a partir da URL 
	 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
	 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a>.
	 * M�todo respons�vel pela quarta etapa do processo de autentica��o. Consiste em obter o token.
	 * 
	 * @throws IllegalArgumentException Lan�a a exce��o {@link java.lang.IllegalArgumentException}
	 * @throws ApiException Lan�a a exce��o {@link com.docusign.esign.client.ApiException}
	 */
	public void getBaseURI() throws IllegalArgumentException, ApiException {
		UserInfo userInfo = apiClient.getUserInfo(apiClient.getAccessToken());

		// currently parsing the first account we find in the response
		apiClient.setBasePath(userInfo.getAccounts().get(0).getBaseUri() + "/restapi");

		Configuration.setDefaultApiClient(apiClient);
	}
	
	/**
	 * M�todo respons�vel pela execu��o da terceira e da quarta etapa de autentica��o.
	 * 
	 * @param accessCode Recebe como par�metro o c�digo de autentica��o obtido na segunda etapa.
	 * @throws IllegalArgumentException Lan�a a exce��o {@link java.lang.IllegalArgumentException}
	 * @throws ApiException Lan�a a exce��o {@link com.docusign.esign.client.ApiException}
	 */
	public String authenticate(String accessCode) throws IllegalArgumentException, ApiException {
		getToken(accessCode);
		getBaseURI();
		return apiClient.getAccessToken();
	}
	
}