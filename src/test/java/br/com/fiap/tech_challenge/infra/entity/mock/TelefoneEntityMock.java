package br.com.fiap.tech_challenge.infra.entity.mock;

import br.com.fiap.tech_challenge.domain.enums.TipoTelefone;
import br.com.fiap.tech_challenge.infra.entity.TelefoneEntity;

public class TelefoneEntityMock {

    public static TelefoneEntity getTelefoneEntity() {
        TelefoneEntity telefone = new TelefoneEntity();
        telefone.setTipoTelefone(TipoTelefone.CELULAR);
        telefone.setDdd("11");
        telefone.setNumero("912341234");
        return telefone;

    }

}
