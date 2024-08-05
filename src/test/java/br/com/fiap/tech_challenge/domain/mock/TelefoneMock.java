package br.com.fiap.tech_challenge.domain.mock;

import br.com.fiap.tech_challenge.domain.TelefoneDTO;
import br.com.fiap.tech_challenge.domain.enums.TipoTelefone;

public class TelefoneMock {

    public static TelefoneDTO getTelefone() {
        TelefoneDTO telefoneDTO = new TelefoneDTO();
        telefoneDTO.setTipoTelefone(TipoTelefone.CELULAR);
        telefoneDTO.setDdd("11");
        telefoneDTO.setNumero("91234-1234");
        return telefoneDTO;

    }

}
