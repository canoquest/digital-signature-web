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
 * @author carlos.costa
 */
public class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {

	/**
	 * {@inheritDoc}
	 */
	public byte[] deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) {
		try {	
			return Base64.decode(json.getAsString());
		} catch (Exception e) {
			throw new JsonParseException("Problemas ao deserializar array de bytes.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public JsonElement serialize(byte[] fileBytes, Type paramType,
			JsonSerializationContext paramJsonSerializationContext) {
		try {
			return new JsonPrimitive(Base64.encodeBytes(fileBytes, Base64.ENCODE));
		} catch (IOException e) {
			throw new JsonParseException("Problemas ao serializar array de bytes.", e);
		}
	}

}