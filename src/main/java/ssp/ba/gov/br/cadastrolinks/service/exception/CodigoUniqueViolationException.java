package ssp.ba.gov.br.cadastrolinks.service.exception;

public class CodigoUniqueViolationException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;

	public CodigoUniqueViolationException(String message) {
        super(message);
    }
}
