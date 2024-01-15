package ssp.ba.gov.br.cadastrolinks.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;

@SuppressWarnings("unchecked")
@Component
public class RequestUtils {

    @Autowired
    private HttpServletRequest request;

    public String pegaVariavelDaURI(String verbo, String variavel) {
        String dado = null;
        if(request.getMethod().equals(verbo)){
          
			Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            dado =  map.get(variavel);
        }
        return dado;
    }

    public String metodoDaRequisicao(){
        return request.getMethod();
    }
}
