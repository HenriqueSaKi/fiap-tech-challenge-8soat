package br.com.fiap.tech_challenge.infra.config;

import br.com.fiap.tech_challenge.infra.repository.ClienteRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = ClienteRepository.class)
public class ClienteRepositoryConfig {
}
