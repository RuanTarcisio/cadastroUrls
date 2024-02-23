package ssp.ba.gov.br.cadastrolinks.service.exception;

public class CpfUniqueViolationException extends RuntimeException {
  
	private static final long serialVersionUID = 1L;

	public CpfUniqueViolationException(String message) {
        super(message);
    }
}
