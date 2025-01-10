package br.com.credsystem.integration.factory;

import br.com.credsystem.integration.api.SerasaAPI;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.concurrent.TimeUnit;

@Configuration
@Setter(onMethod_ = @Autowired)
public class SerasaIntegrationFactory {
    Environment env;

    @Bean
    public SerasaAPI serasaAPI() {
        String host = env.getProperty("serasa.host");
        return Feign.builder()
                .options(new Request.Options(10L, TimeUnit.SECONDS,10L, TimeUnit.SECONDS, true))
                .logger(new feign.slf4j.Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(SerasaAPI.class,host);
    }
}
