package br.com.fiap.tech_challenge.infra.config;

import br.com.fiap.tech_challenge.TechChallengeApplication;
import br.com.fiap.tech_challenge.domain.repository.ProdutoRepositoryPort;
import br.com.fiap.tech_challenge.domain.service.ProdutoService;
import br.com.fiap.tech_challenge.domain.service.impl.ProdutoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TechChallengeApplication.class)
public class ProdutoServiceConfig {

    @Bean
    ProdutoService produtoService(ProdutoRepositoryPort repository) {
        return new ProdutoServiceImpl(repository);
    }

}
