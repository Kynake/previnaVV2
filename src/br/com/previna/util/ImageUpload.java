package br.com.previna.util;

import com.cloudinary.Cloudinary;
//import com.cloudinary.Transformation;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;

import br.com.previna.exception.ImageUploadException;

public class ImageUpload {

	// https://cloudinary.com/documentation/java_integration#configuration
	private static Cloudinary cloudinary = new Cloudinary(getCloudinaryParams());

	/**
	 * Obtém as linhas de configuração do arquivo config.properties que iniciam com "cloudinary".<br>
	 * Caso exista mais de uma linha com a mesma chave, apenas a primeira encontrada é considerada.
	 * @return Map com as configurações lidas para passar para o Cloudinary.
	 * @author Calebe
	 */
	private static Map getCloudinaryParams() {
		Map<String, Object> params = new HashMap<>();
		Properties props = ReadProperties.read();
		Enumeration propKeys = props.propertyNames();
		while(propKeys.hasMoreElements()) {
			String key = (String) propKeys.nextElement();
			if(key.startsWith("cloudinary.")) {
				String paramName = key.substring(11);
				if(!params.containsKey(paramName))
					params.put(paramName, props.getProperty(key));
			}
		}

		return params;
	}

	/**
	 * Faz upload da imagem especificada. Apenas imagens em base64 são aceitas no momento.
	 * @param base64img Imagem codificada em base64
	 * @param fileName Nome do arquivo de imagem
	 * @return URL da imagem
	 * @throws ImageUploadException Exceção de upload de imagem
	 * @author Calebe
	 */
	public static String upload(String base64img, String fileName) throws ImageUploadException {

		if(base64img.length() < 1) return null;
		Map<String, Object> params = new HashMap<>();
		//params.put("transformation", new Transformation().width(1600).height(1600).crop("limit")); // Limite de tamanho
		params.put("timeout", 30000); // Timeout do envio em milissegundos
		if(fileName != null && fileName.length() > 0)
			params.put("public_id", fileName);
		try {
			Map imageUpload = cloudinary.uploader().upload(base64img, params);
			if (imageUpload.containsKey("error"))
				throw new ImageUploadException("HTTP" + imageUpload.get("http_code") + ": " + ((Map) imageUpload.get("error")).get("message"));

			return imageUpload.get("url").toString();
		} catch(IOException e) {
			throw new ImageUploadException(e);
		}
	}

	public static String upload(String base64img) throws IOException, ImageUploadException {
		return upload(base64img, "");
	}

	/**
	 * Processa a mensagem contida em ImageUploadException.<br>
	 * O processamento consiste em verificar se há um código de status HTTP na mensagem de erro. Quando há, a mensagem está no formato "HTTPXXX: mensagem".
	 * Neste caso, separa o código e o restante da mensagem em um Map e retorna.
	 * @param exceptionMessage Mensagem de erro da exceção (e.getMessage())
	 * @return Map com duas entradas:<br>
	 *  <ul>
	 * 	<li><b>http_code</b> -> Objeto <i>javax.ws.rs.core.Response.Status</i> com o código de retorno HTTP<br>
	 * 	<li><b>message</b> -> Mensagem de erro<br>
	 * 	</ul>
	 * Caso não seja possível extrair o código HTTP, retorna BAD_REQUEST.
	 * @author Calebe
	 */
	public static Map handleImageUploadException(String exceptionMessage) {
		Map<String, Object> mapError = new HashMap<>();
		mapError.put("http_code", Response.Status.BAD_REQUEST);
		mapError.put("message", exceptionMessage);

		Pattern regex = Pattern.compile("^HTTP([2-5][0-1][0-9]): (.*)$");
		Matcher m = regex.matcher(exceptionMessage);
		if(m.matches()) {
			int httpCode = -1;
			try {
				httpCode = Integer.parseInt(m.group(1));
			} catch(NumberFormatException ne) {
				//httpCode = -1;
			}
			Response.Status httpStatus = Response.Status.fromStatusCode(httpCode);
			if(httpStatus != null) {
				mapError.replace("http_code", httpStatus);
				mapError.replace("message", m.group(2));
			}
		}

		return mapError;
	}
}
