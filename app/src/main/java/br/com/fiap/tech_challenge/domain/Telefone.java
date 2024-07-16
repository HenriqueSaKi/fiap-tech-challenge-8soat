package br.com.fiap.tech_challenge.domain;

import br.com.fiap.tech_challenge.domain.enums.TipoTelefone;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Telefone {

    @Schema(name = "tipoTelefone", description = "Tipo Telefone")
    private TipoTelefone tipoTelefone;

    @Schema(name = "ddd", description = "DDD", example = "11")
    private String ddd;

    @Schema(name = "numero", description = "Número de telefone", example = "912341234")
    private String numero;

}
