package br.com.previna.exception;

import java.io.Serializable;

/**
 * Exceções de upload de imagem
 * @author Calebe
 */
public class ImageUploadException extends Exception {

	private static final long serialVersionUID = -6062842245021593531L;

	public ImageUploadException(String msg) {
		super(msg);
	}

	public ImageUploadException(Exception e) {
		super(e);
	}

	public ImageUploadException(String msg, Exception e) {
		super(msg, e);
	}
}
