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
 * Classe Builder responsável por executar o consumo dos serviços em Rest.
 * Tem a capacidade de executar todos os métodos previsto na API Rest de acordo com o
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
	 * @param host - Host da API de serviços
	 * @param path - Resource da API de serviços que está sendo solicitado
	 * @param mediaType - Mime Type da mensagem de retorno
	 * @param token - Token de autorização para consumo do recurso solicitado
	 * @param parameters - Parâmetros informados para a requisição
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
	 * @param host - Host da API de serviços
	 * @param path - Resource da API de serviços que está sendo solicitado
	 * @param mediaType - Mime Type da mensagem de retorno 
	 * @param parameters - Parâmetros informados para a requisição
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
	 * Todos os recursos da classes, com exceção de parameters, devem
	 * ser inicializados pelo construtor.
	 *
	 * @param host - Host da API de serviços
	 * @param path - Resource da API de serviços que está sendo solicitado
	 * @param mediaType - Mime Type da mensagem de retorno
	 * @param token - Token de autorização para consumo do recurso solicitado
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
	 * Todos os recursos da classes, com exceção do token e de parameters, devem ser
	 * inicializados pelo construtor.
	 *
	 * @param host - Host da API de serviços
	 * @param path - Resource da API de serviços que está sendo solicitado
	 * @param mediaType - Mime Type da mensagem de retorno
	 */
	public APIClientBuilder(final String host, final String path, final String mediaType) {
		super();
		this.host = host;
		this.path = path;
		this.mediaType = mediaType;
	}

	/**
	 * Método da API {@link br.com.unicred.restwsclient.base.builder.WSConnectionBuilder}
	 * responsável pelas requisições GET em serviços Rest.
	 * Retorna um objeto do tipo {@link javax.ws.rs.core.Response},
	 * contendo as informações do retorno da requisição GET.
	 *
	 * @return - Retorna um objeto do tipo {@link javax.ws.rs.core.Response}, que encapsula informações
	 * 					ao nível do proticilo HTTP.
	 * @throws APIClientException - Lança uma exceção do tipo
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
	 * Método da API {@link br.com.unicred.restwsclient.base.builder.WSConnectionBuilder}
	 * responsável pelas requisições POST em serviços Rest.
	 * Retorna um objeto do tipo {@link javax.ws.rs.core.Response},
	 * contendo as informações do retorno da requisição POST.
	 *
	 * @param json - Mensagem em formato JSON criada a partir da transformação do objeto que
	 * 					encapsula as informações de envio. Essa mensagem JSON contém as informações
	 * 					de envio para o serviço.
	 * @return - Retorna um objeto do tipo {@link javax.ws.rs.core.Response}, que encapsula informações
	 * 					ao nível do proticilo HTTP.
	 * @throws APIClientException - Lança uma exceção do tipo
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
	 * Método da API {@link br.com.unicred.restwsclient.base.builder.WSConnectionBuilder}
	 * responsável pelas requisições PUT em serviços Rest.
	 * Retorna um objeto do tipo {@link javax.ws.rs.core.Response},
	 * contendo as informações do retorno da requisição PUT.
	 *
	 * @param json - Mensagem em formato JSON criada a partir da transformação do objeto que
	 * 					encapsula as informações de envio. Essa mensagem JSON contém as informações
	 * 					de envio para o serviço.
	 *
	 * @return - Retorna um objeto do tipo {@link javax.ws.rs.core.Response}, que encapsula informações
	 * 					de retorno da requisição Rest ao nível do protocolo HTTP.
	 * @throws APIClientException Lança uma exceção do tipo
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
	 * Método responsável pela criação do objeto {@link javax.ws.rs.client.Invocation.Builder},
	 * que encapsula as informações de Host, Resource, Mime Type, Header e QueryParams.
	 *
	 * @return - Retorna um objeto {@link @link javax.ws.rs.client.Invocation.Builder}
	 * @throws APIClientException - Lança uma exceção do tipo
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
	 * Formata a data no padrão da Atena.
	 *
	 * @param dateParam Parâmetor GET que irá na URL da chamada do serviço;
	 * @return data formatada.
	 */
	private String formatDateParameter(final Date dateParam) {

		return DateUtil.format(dateParam, DEFAULT_DATE_PARAM_PATTERN);

	}

	/**
	 * Método responsável pela criação do objeto {@link javax.ws.rs.client.WebTarget}, qualificado
	 * com as configurações de Host e Resource que estão sendo solicitados do serviço Rest desejado.
	 * Exemplo: <br />
	 * - <b>Host</b>: www.example.com <br />
	 * - <b>Resource</b>: /clientes
	 *
	 * @return - Retorna um objeto do tipo {@link javax.ws.rs.client.WebTarget}
	 * @throws APIClientException - Lança uma exceção do tipo
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
	 * Retorna o host utilizado na requisição Rest.
	 *
	 * @return - Retorna o Host da API de serviços
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Recebe o host que deve ser utilizado na requisição Rest.
	 *
	 * @param host - Recebe o Host da API de serviços
	 */
	public void setHost(final String host) {
		this.host = host;
	}

	/**
	 * Retorna o path utilizado na requisição Rest.
	 *
	 * @return - Retorna o Resource que está sendo solicitado
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Recebe o path que deve ser utilizado na requisição Rest.
	 *
	 * @param path - Define o Resource que deve ser solicitado
	 */
	public void setPath(final String path) {
		this.path = path;
	}

	/**
	 * Retorna o tipo MIME TYPE utilizado na requisição Rest.
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
	 * Recebe o tipo MIME TYPE que deve ser utilizado na requisição Rest.
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
	 * Retorna o token de autenticação utilizado na requisição Rest.
	 *
	 * @return - Retorna o Token de autorização para a solicitação dos Resources
	 * 			 da API de serviços
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Recebe o token de autenticação utilizado na requisição Rest.
	 *
	 * @param token - Recebe o Token de autorização para a solicitação dos Resources
	 * 			 da API de serviços
	 */
	public void setToken(final String token) {
		this.token = token;
	}

	/**
	 * Retorna um Map de parâmetros informados na requisição.
	 *
	 * @return - Retorna um Map de parâmetros
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}

	/**
	 * Recebe um Map de parâmetros para serem utilizados na requisição
	 *
	 * @param parameters - Recebe um Map de parâmetros para serem utilizados na requisição
	 */
	public void setParameters(final Map<String, Object> parameters) {
		this.parameters = parameters;
	}

}