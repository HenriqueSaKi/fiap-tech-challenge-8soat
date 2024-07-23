package br.com.fiap.tech_challenge.infra.config;

import br.com.fiap.tech_challenge.TechChallengeApplication;
import br.com.fiap.tech_challenge.domain.repository.PedidoRepositoryPort;
import br.com.fiap.tech_challenge.domain.services.PedidoService;
import br.com.fiap.tech_challenge.domain.services.impl.PedidoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TechChallengeApplication.class)
public class PedidoServiceConfig {

    @Bean
    PedidoService pedidoService(PedidoRepositoryPort repository) {
        return new PedidoServiceImpl(repository);
    }

}
