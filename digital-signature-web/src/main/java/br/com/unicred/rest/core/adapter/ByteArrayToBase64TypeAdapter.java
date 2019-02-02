package br.com.unicred.rest.core.adapter;

import java.io.IOException;
import java.lang.reflect.Type;

import org.jboss.resteasy.util.Base64;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Adapter para serializar e deserializar array de bytes em JSON dos serviços Rest.
 *
 * @author rodrigo.prates
 */
public class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {

	/**
	 * {@inheritDoc}
	 */
	public byte[] deserialize(final JsonElement json, final Type typeOfT,
			final JsonDeserializationContext context) {
		try {	
			return Base64.decode(json.getAsString());
		} catch (final Exception e) {
			throw new JsonParseException("Problemas ao deserializar array de bytes.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public JsonElement serialize(final byte[] fileBytes, final Type paramType,
			final JsonSerializationContext paramJsonSerializationContext) {
		try {
			return new JsonPrimitive(Base64.encodeBytes(fileBytes, Base64.ENCODE));
		} catch (final IOException e) {
			throw new JsonParseException("Problemas ao serializar array de bytes.", e);
		}
	}

}
