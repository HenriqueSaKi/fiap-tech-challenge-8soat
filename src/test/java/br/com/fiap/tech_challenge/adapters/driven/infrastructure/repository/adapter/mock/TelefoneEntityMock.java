package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.mock;

import br.com.fiap.tech_challenge.core.domain.model.enums.TipoTelefone;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.TelefoneEntity;

public class TelefoneEntityMock {

    public static TelefoneEntity getTelefoneEntity() {
        TelefoneEntity telefone = new TelefoneEntity();
        telefone.setTipoTelefone(TipoTelefone.CELULAR);
        telefone.setDdd("11");
        telefone.setNumero("912341234");
        return telefone;

    }

}
