package br.com.fiap.tech_challenge.core.domain.mock;

import br.com.fiap.tech_challenge.core.domain.model.Telefone;
import br.com.fiap.tech_challenge.core.domain.model.enums.TipoTelefone;

public class TelefoneMock {

    public static Telefone getTelefone() {
        Telefone telefone = new Telefone();
        telefone.setTipoTelefone(TipoTelefone.CELULAR);
        telefone.setDdd("11");
        telefone.setNumero("91234-1234");
        return telefone;

    }

}
