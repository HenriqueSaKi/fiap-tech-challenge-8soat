package br.com.fiap.tech_challenge.infra.config;

import br.com.fiap.tech_challenge.infra.repository.PedidoRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = PedidoRepository.class)
public class PedidoRepositoryConfig {
}
