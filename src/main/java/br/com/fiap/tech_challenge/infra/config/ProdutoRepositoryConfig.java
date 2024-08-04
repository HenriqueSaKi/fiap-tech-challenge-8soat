package br.com.fiap.tech_challenge.infra.config;

import br.com.fiap.tech_challenge.infra.repository.ProdutoRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = ProdutoRepository.class)
public class ProdutoRepositoryConfig {
}
