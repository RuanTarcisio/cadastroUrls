package ssp.ba.gov.br.cadastrolinks.domain.converter;

import java.io.IOException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ssp.ba.gov.br.cadastrolinks.domain.Link;
import ssp.ba.gov.br.cadastrolinks.dto.LinkNewDto;
import ssp.ba.gov.br.cadastrolinks.util.FaviconUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LinkConverter {

	public static Link toLink(LinkNewDto dto) {

		String favicon = null;	
		
		if (dto.getFavicon() == null && dto.getBaseUrl() != null) {
/*		
 * 				ATENÇAO CRIAR EXCEPTION
*/			
			try {
				favicon = FaviconUtils.returnFavicon(dto.getBaseUrl());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return Link.builder()
					.endereco(dto.getEndereco())
					.titulo(dto.getTitulo())
					.favicon(favicon)
					.build();
		}
		
		return Link.builder()
				.endereco(dto.getEndereco())
				.favicon(dto.getFavicon())
				.titulo(dto.getTitulo())
				.build();

	}

}
