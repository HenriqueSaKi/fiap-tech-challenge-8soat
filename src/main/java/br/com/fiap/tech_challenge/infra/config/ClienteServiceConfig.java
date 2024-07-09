package br.com.fiap.tech_challenge.infra.config;

import br.com.fiap.tech_challenge.TechChallengeApplication;
import br.com.fiap.tech_challenge.domain.repository.ClienteRepositoryPort;
import br.com.fiap.tech_challenge.domain.services.ClienteService;
import br.com.fiap.tech_challenge.domain.services.impl.ClienteServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TechChallengeApplication.class)
public class ClienteServiceConfig {

    @Bean
    ClienteService clienteService(ClienteRepositoryPort repositoryPort) {
        return new ClienteServiceImpl(repositoryPort);
    }

}
