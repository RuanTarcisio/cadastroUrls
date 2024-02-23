package ssp.ba.gov.br.cadastrolinks.service.validation.templ;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	private Integer status;
	private LocalDateTime timestamp;
	private String type;
	private String title;
	private String detail;
	private String userMessage;
	private List<Field> fields;
	
	@Getter
	@Builder
	@AllArgsConstructor
	public static class Field {
		
		private String name;
		private String userMessage;
		
	}
	
}
