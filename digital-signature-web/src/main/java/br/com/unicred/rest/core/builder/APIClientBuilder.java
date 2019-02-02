package br.com.unicred.rest.core.builder;

import java.util.Date;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import br.com.unicred.rest.core.enumeration.APIParameterEnum;
import br.com.unicred.rest.core.exception.APIClientException;
import br.com.unicred.rest.core.util.DateUtil;

/**
 * Classe Builder respons�vel por executar o consumo dos servi�os em Rest.
 * Tem a capacidade de executar todos os m�todos previsto na API Rest de acordo com o
 * protocolo HTTP.
 *
 * @author carlos.costa
 * @version 1.0
 */
public class APIClientBuilder {

	public static final String DEFAULT_DATE_PARAM_PATTERN = "yyyy-MM-dd";

	private String host;
	private String path;
	private String mediaType;
	private String token;
	private Map<String, Object> parameters;

	/**
	 * Construtor da classe WSConnectionBuilder.
	 * Todos os recursos da classes devem ser inicializados pelo construtor.
	 *
	 * @param host - Host da API de servi�os
	 * @param path - Resource da API de servi�os que est� sendo solicitado
	 * @param mediaType - Mime Type da mensagem de retorno
	 * @param token - Token de autoriza��o para consumo do recurso solicitado
	 * @param parameters - Par�metros informados para a requisi��o
	 */
	public APIClientBuilder(final String host, final String path, final String mediaType,
			final String token, final Map<String, Object> parameters) {
		super();
		this.host = host;
		this.path = path;
		this.mediaType = mediaType;
		this.token = token;
		this.parameters = parameters;
	}
	
	/**
	 * Construtor da classe WSConnectionBuilder.
	 * Todos os recursos da classes devem ser inicializados pelo construtor.
	 *
	 * @param host - Host da API de servi�os
	 * @param path - Resource da API de servi�os que est� sendo solicitado
	 * @param mediaType - Mime Type da mensagem de retorno 
	 * @param parameters - Par�metros informados para a requisi��o
	 */
	public APIClientBuilder(final String host, final String path, final String mediaType,
			final Map<String, Object> parameters) {
		super();
		this.host = host;
		this.path = path;
		this.mediaType = mediaType;		
		this.parameters = parameters;
	}

	/**
	 * Construtor da classe WSConnectionBuilder.
	 * Todos os recursos da classes, com exce��o de parameters, devem
	 * ser inicializados pelo construtor.
	 *
	 * @param host - Host da API de servi�os
	 * @param path - Resource da API de servi�os que est� sendo solicitado
	 * @param mediaType - Mime Type da mensagem de retorno
	 * @param token - Token de autoriza��o para consumo do recurso solicitado
	 */
	public APIClientBuilder(final String host, final String path, final String mediaType,
			final String token) {
		super();
		this.host = host;
		this.path = path;
		this.mediaType = mediaType;
		this.token = token;
	}

	/**
	 * Construtor da classe WSConnectionBuilder.
	 * Todos os recursos da classes, com exce��o do token e de parameters, devem ser
	 * inicializados pelo construtor.
	 *
	 * @param host - Host da API de servi�os
	 * @param path - Resource da API de servi�os que est� sendo solicitado
	 * @param mediaType - Mime Type da mensagem de retorno
	 */
	public APIClientBuilder(final String host, final String path, final String mediaType) {
		super();
		this.host = host;
		this.path = path;
		this.mediaType = mediaType;
	}

	/**
	 * M�todo da API {@link br.com.unicred.restwsclient.base.builder.WSConnectionBuilder}
	 * respons�vel pelas requisi��es GET em servi�os Rest.
	 * Retorna um objeto do tipo {@link javax.ws.rs.core.Response},
	 * contendo as informa��es do retorno da requisi��o GET.
	 *
	 * @return - Retorna um objeto do tipo {@link javax.ws.rs.core.Response}, que encapsula informa��es
	 * 					ao n�vel do proticilo HTTP.
	 * @throws APIClientException - Lan�a uma exce��o do tipo
	 * 						{@link br.com.unicred.FacadeException.base.exception.APIClientException}
	 */
	public Response get() throws APIClientException {
		try {
			return createBuilder().get(Response.class);
		}  catch (final Exception ex) {
			throw new APIClientException(ex.getMessage());
		}
	}

	/**
	 * M�todo da API {@link br.com.unicred.restwsclient.base.builder.WSConnectionBuilder}
	 * respons�vel pelas requisi��es POST em servi�os Rest.
	 * Retorna um objeto do tipo {@link javax.ws.rs.core.Response},
	 * contendo as informa��es do retorno da requisi��o POST.
	 *
	 * @param json - Mensagem em formato JSON criada a partir da transforma��o do objeto que
	 * 					encapsula as informa��es de envio. Essa mensagem JSON cont�m as informa��es
	 * 					de envio para o servi�o.
	 * @return - Retorna um objeto do tipo {@link javax.ws.rs.core.Response}, que encapsula informa��es
	 * 					ao n�vel do proticilo HTTP.
	 * @throws APIClientException - Lan�a uma exce��o do tipo
	 * 						{@link br.com.unicred.FacadeException.base.exception.APIClientException}
	 */
	public Response post(final String json) throws APIClientException {
		try {
			return createBuilder().post(Entity.entity(json, mediaType));
		} catch (final Exception ex) {
			throw new APIClientException(ex.getMessage());
		}
	}

	/**
	 * M�todo da API {@link br.com.unicred.restwsclient.base.builder.WSConnectionBuilder}
	 * respons�vel pelas requisi��es PUT em servi�os Rest.
	 * Retorna um objeto do tipo {@link javax.ws.rs.core.Response},
	 * contendo as informa��es do retorno da requisi��o PUT.
	 *
	 * @param json - Mensagem em formato JSON criada a partir da transforma��o do objeto que
	 * 					encapsula as informa��es de envio. Essa mensagem JSON cont�m as informa��es
	 * 					de envio para o servi�o.
	 *
	 * @return - Retorna um objeto do tipo {@link javax.ws.rs.core.Response}, que encapsula informa��es
	 * 					de retorno da requisi��o Rest ao n�vel do protocolo HTTP.
	 * @throws APIClientException Lan�a uma exce��o do tipo
	 * 						{@link br.com.unicred.FacadeException.base.exception.APIClientException}
	 */
	public Response put(final String json)
			throws APIClientException {
		try {
			return createBuilder().put(Entity.entity(json, mediaType));
		} catch (final Exception ex) {
			throw new APIClientException(ex.getMessage());
		}
	}

	/**
	 * M�todo respons�vel pela cria��o do objeto {@link javax.ws.rs.client.Invocation.Builder},
	 * que encapsula as informa��es de Host, Resource, Mime Type, Header e QueryParams.
	 *
	 * @return - Retorna um objeto {@link @link javax.ws.rs.client.Invocation.Builder}
	 * @throws APIClientException - Lan�a uma exce��o do tipo
	 * 			{@link br.com.unicred.FacadeException.base.exception.APIClientException}
	 */
	private Builder createBuilder() throws APIClientException {
		try {
			WebTarget webTarget = createWebTarget();

			if (parameters != null && !parameters.isEmpty()) {

				for (final Map.Entry<String, Object> parameter : parameters.entrySet()) {

					final String key = parameter.getKey();
					final Object value = parameter.getValue();

					if (value != null && value instanceof Date) {

						final Date valueAsDate = (Date) value;

						final String formattedDateParameter = formatDateParameter(valueAsDate);
						webTarget = webTarget.queryParam(key, formattedDateParameter != null ? formattedDateParameter : value);

						continue;

					}

					webTarget = webTarget.queryParam(key, value);
				}

			}

			final Builder builder = webTarget.request(mediaType);

			builder.header(APIParameterEnum.CONTENT_TYPE.getKey(), APIParameterEnum.CONTENT_TYPE.getValue());			

			return builder;
		} catch (final Exception ex) {
			throw new APIClientException(ex);
		}
	}

	/**
	 * Formata a data no padr�o da Atena.
	 *
	 * @param dateParam Par�metor GET que ir� na URL da chamada do servi�o;
	 * @return data formatada.
	 */
	private String formatDateParameter(final Date dateParam) {

		return DateUtil.format(dateParam, DEFAULT_DATE_PARAM_PATTERN);

	}

	/**
	 * M�todo respons�vel pela cria��o do objeto {@link javax.ws.rs.client.WebTarget}, qualificado
	 * com as configura��es de Host e Resource que est�o sendo solicitados do servi�o Rest desejado.
	 * Exemplo: <br />
	 * - <b>Host</b>: www.example.com <br />
	 * - <b>Resource</b>: /clientes
	 *
	 * @return - Retorna um objeto do tipo {@link javax.ws.rs.client.WebTarget}
	 * @throws APIClientException - Lan�a uma exce��o do tipo
	 * 					{@link br.com.unicred.FacadeException.base.exception.APIClientException}
	 */
	private WebTarget createWebTarget() throws APIClientException {
		try {
			return ClientBuilder.newClient()
					.target(host)
			        .path(path);
		} catch (final Exception ex) {
			throw new APIClientException(ex.getMessage());
		}
	}

	/**
	 * Retorna o host utilizado na requisi��o Rest.
	 *
	 * @return - Retorna o Host da API de servi�os
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Recebe o host que deve ser utilizado na requisi��o Rest.
	 *
	 * @param host - Recebe o Host da API de servi�os
	 */
	public void setHost(final String host) {
		this.host = host;
	}

	/**
	 * Retorna o path utilizado na requisi��o Rest.
	 *
	 * @return - Retorna o Resource que est� sendo solicitado
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Recebe o path que deve ser utilizado na requisi��o Rest.
	 *
	 * @param path - Define o Resource que deve ser solicitado
	 */
	public void setPath(final String path) {
		this.path = path;
	}

	/**
	 * Retorna o tipo MIME TYPE utilizado na requisi��o Rest.
	 * O MIME TYPE sempre deve uma das constantes do Enum {@link javax.ws.rs.core.MediaType},
	 * tais como:<br />
	 * - <b>MediaType.APPLICATION_JSON</b><br />
	 * - <b>MediaType.TEXT_XML</b><br />
	 *
	 * @return - Retorna o Mime Type de mensagem de retorno
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * Recebe o tipo MIME TYPE que deve ser utilizado na requisi��o Rest.
	 * O MIME TYPE sempre deve uma das constantes do Enum {@link javax.ws.rs.core.MediaType},
	 * tais como:<br />
	 * - <b>MediaType.APPLICATION_JSON</b><br />
	 * - <b>MediaType.TEXT_XML</b><br />
	 *
	 * @param mediaType - Recebe o Mime Type da mensagem de retorno
	 */
	public void setMediaType(final String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * Retorna o token de autentica��o utilizado na requisi��o Rest.
	 *
	 * @return - Retorna o Token de autoriza��o para a solicita��o dos Resources
	 * 			 da API de servi�os
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Recebe o token de autentica��o utilizado na requisi��o Rest.
	 *
	 * @param token - Recebe o Token de autoriza��o para a solicita��o dos Resources
	 * 			 da API de servi�os
	 */
	public void setToken(final String token) {
		this.token = token;
	}

	/**
	 * Retorna um Map de par�metros informados na requisi��o.
	 *
	 * @return - Retorna um Map de par�metros
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}

	/**
	 * Recebe um Map de par�metros para serem utilizados na requisi��o
	 *
	 * @param parameters - Recebe um Map de par�metros para serem utilizados na requisi��o
	 */
	public void setParameters(final Map<String, Object> parameters) {
		this.parameters = parameters;
	}

}