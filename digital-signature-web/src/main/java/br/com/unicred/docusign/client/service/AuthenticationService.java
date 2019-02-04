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
 * Classe responsável pelas etapas de autenticação na API DocuSign. <br>
 * 
 * Todos os códigos fonte implementados nessa classe, bem como as devidas explicações sobre como obter 
 * as informações de <b>SECRET_KEY</b>, <b>INTEGRATION_KEY</b>, <b>REDIRECT_URI</b>, <b>API_ACCOUNT</b> e 
 * <b>API_USERNAME</b> estão disponíveis na URL 
 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> da documentação 
 * oficial da API do DocuSign. <br><br>
 * 
 * A autenticação consiste em quatro etapas: <br>
 * 1ª) Configuração dos parâmetros <b>INTEGRATOR KEY</b>, <b>SECRET KEY</b> e <b>REDIRECT URI</b>. 
 * As informações para as configurações dessas propriedades estão disponíveis na URL
 * <a href="https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant">
 * https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant</a> na sessão
 * <b>Prerequisites</b>. <br>
 * 
 * 2ª) Consiste em obter o código de acesso. Mais informações na URL 
 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> na sessão 
 * <b>Configure Client and Request Authorization Code</b>. <br>
 * 
 * 3ª) Consiste em obter o token. Mais informações na URL 
 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> na sessão
 * <b>Request Authentication Token</b>. <br>
 * 
 * 4ª) Consiste em obter a URI base. Mais informações na URL 
 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> na sessão
 * <b>Get base URI</b>. <br> 
 * 
 * <b>Observação:</b> <br>
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
	
	/** Código de acesso obtido após a segunda etapa de autenticação.
	 *  As informações para obter esse código de acesso estão disponíevis na URL 
	 *  <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
	 *  https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> na sessão
	 *  <b>Configure Client and Request Authorization Code</b> 
	 */
	@SuppressWarnings("unused")
	private static final String ACCESS_CODE = "eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQkAAAABAAYABwAAOCN4CXvWSAgAAMSpvwl71kgCAByxagEByrBAk64qkZba5QIVAAEAAAAYAAEAAAAFAAAADQAkAAAAYTViMDZmOTctNGI4OC00ODFjLTkwYmItMjhhZGM5NWQ1YmFlMAAAOCN4CXvWSBIAAQAAAAsAAABpbnRlcmFjdGl2ZQ.JZA6UEv7v80Mbw7rCBW9ICr867YvWswK3Z1-C2Xf3wogpX5ZlS86TxRYU9ejB7Fo9GZlblpK_SLIyhDmuWM7PbEOIHlocKL0SjdqQBa4RsZdqXEvoC1AyaKgbAUO2D-SHxrZZhuv43FWgjwTfreSUj3YiNxA5D8o5tcERujlkFV30IkBNQ_T8k3b_HAOjcsVGW00twcvBNPUA6lGS0dojQnaUgfslQz69TO7pXqekm9_ePhEtaVU2QqNnrKl8lBt1CNV0j1YIMBPZdhX3y0sG0NtAc89UarJSgaKEEmz4pnxHcB77S8NyiCoFVg02yY7lFXs9fv-jKsDLv7005vV1g";
	
	/** Obtido no Admin do Sandbox. URL 
	 *  <a href="https://admindemo.docusign.com/api-integrator-key">
	 *  https://admindemo.docusign.com/api-integrator-key</a> 
	 *  As informações para obter a Secret Key estão disponíveis na URL 
	 *  <a href="https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant">
	 *  https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant</a> 
	 *  na sessão <b>Prerequisites</b>
	 */
	private static final String SECRET_KEY = "0da4be6a-a2a6-4529-98fd-5a18007a29e6";
	
	/** Obtido no Admin do Sandbox. URL 
	 *  <a href="https://admindemo.docusign.com/api-integrator-key">
	 *  https://admindemo.docusign.com/api-integrator-key</a> 
	 *  As informações para obter a Integrator Key estão disponíveis na URL 
	 *  <a href="https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant">
	 *  https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant</a> 
	 *  na sessão <b>Prerequisites</b>
	 */
	private static final String INTEGRATOR_KEY = "a5b06f97-4b88-481c-90bb-28adc95d5bae";
	
	/** Obtido no Admin do Sandbox. URL 
	 *  <a href="https://admindemo.docusign.com/api-integrator-key">
	 *  https://admindemo.docusign.com/api-integrator-key</a> 
	 *  As informações para obter a Redirect URI estão disponíveis na URL 
	 *  <a href="https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant">
	 *  https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant</a> 
	 *  na sessão <b>Prerequisites</b>
	 */
	// http://localhost:8080/docusign-web/application/modules/embedded/embedded.jsp
	private static final String REDIRECT_URI = "http://localhost:8080/digital-signature-web/application/modules/provider/docusign.jsf";	
	
	/** URL informada no código fonte de exemplo disponível em 
	 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
	 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a> 
	 */
	private static final String AUTH_SERVER_URL = "https://account-d.docusign.com";
	
	private ApiClient apiClient;
	
	private static ApiClient apiClientStatic;
	
	/**
	 * Método construtor padrão.
	 */
	public AuthenticationService() {
		super();
	}
	
	/**
	 * Método responsável pela criação do objeto {@link com.docusign.esign.client.ApiClient}
	 * 
	 * @return Retorna uma instância de {@link com.docusign.esign.client.ApiClient}
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
	 * Método responsável por atualizar a propriedade estática apiClientStatic.
	 * 
	 * @param apiClient Recebe uma instância de {@link com.docusign.esign.client.ApiClient}
	 */
	private void setApiClient(ApiClient apiClient) {
		apiClientStatic = apiClient;
	}
	
	/**
	 * Esse método executa o código fonte apresentado na sessão <b>Configure Client and Request Authorization Code</b>.
	 * Essa sessão pode ser acessada a partir da URL 
	 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
	 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a>.
	 * Método responsável pela segunda etapa do processo de autenticação. Consiste em obter o código de autenticação.
	 * 
	 * @throws IOException Lança a exceção {@link java.io.IOException}
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
	 * Esse método executa o código fonte apresentado na sessão <b>Request Authentication Token</b>.
	 * Essa sessão pode ser acessada a partir da URL 
	 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
	 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a>.
	 * Método responsável pela terceira etapa do processo de autenticação. Consiste em obter o token.
	 * 
	 * @param accessCode - Código de acesso obtido na primeira etapa do processo de autenticação.
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
	 * Esse método executa o código fonte apresentado na sessão <b>Get base URI</b>.
	 * Essa sessão pode ser acessada a partir da URL 
	 * <a href="https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth">
	 * https://developers.docusign.com/esign-rest-api/code-examples/config-and-auth</a>.
	 * Método responsável pela quarta etapa do processo de autenticação. Consiste em obter o token.
	 * 
	 * @throws IllegalArgumentException Lança a exceção {@link java.lang.IllegalArgumentException}
	 * @throws ApiException Lança a exceção {@link com.docusign.esign.client.ApiException}
	 */
	public void getBaseURI() throws IllegalArgumentException, ApiException {
		UserInfo userInfo = apiClient.getUserInfo(apiClient.getAccessToken());

		// currently parsing the first account we find in the response
		apiClient.setBasePath(userInfo.getAccounts().get(0).getBaseUri() + "/restapi");

		Configuration.setDefaultApiClient(apiClient);
	}
	
	/**
	 * Método responsável pela execução da terceira e da quarta etapa de autenticação.
	 * 
	 * @param accessCode Recebe como parâmetro o código de autenticação obtido na segunda etapa.
	 * @throws IllegalArgumentException Lança a exceção {@link java.lang.IllegalArgumentException}
	 * @throws ApiException Lança a exceção {@link com.docusign.esign.client.ApiException}
	 */
	public String authenticate(String accessCode) throws IllegalArgumentException, ApiException {
		getToken(accessCode);
		getBaseURI();
		return apiClient.getAccessToken();
	}
	
}