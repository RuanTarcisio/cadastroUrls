package ssp.ba.gov.br.cadastrolinks.util;

import lombok.experimental.UtilityClass;
import ssp.ba.gov.br.cadastrolinks.domain.Usuario;

@UtilityClass
public class UsuarioUtils {

    public static Usuario getUsuarioLogado(){
        return  null; //(Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
