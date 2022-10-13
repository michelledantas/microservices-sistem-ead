package com.ead.authuser.configs;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration //informamos essa anotação para o Spring saber que vai ter que ler essas configurações iniciais
public class ResolverConfig extends WebMvcConfigurationSupport{

    /*Resolver, converter esses parametros em tipos primitivos do java de contexto em parametros
     em cada uma dessas solicitacoes que forem feitas */

    @Override
    public void addArgumentResolvers (List<HandlerMethodArgumentResolver> argumentResolvers){
        // estamos passando o conjunto de especificacoes
        argumentResolvers.add(new SpecificationArgumentResolver());
        PageableArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        //estamos passando a paginação
        argumentResolvers.add(resolver);
        //esse método é da classe WebMvcConfigurationSupport
        //Então agora foi feita a configuração para sempre que receber essas solicitaçoes com esses parametros de forma indireta
        //ele conseguir fazer essa conversao para essas tipagens Java Básico
        super.addArgumentResolvers(argumentResolvers);
    }
}
