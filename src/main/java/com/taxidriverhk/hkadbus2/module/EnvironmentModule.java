package com.taxidriverhk.hkadbus2.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import lombok.extern.log4j.Log4j2;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class EnvironmentModule extends AbstractModule {

    public Properties applicationProperties() {
        final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(System.getenv("ENCRYPTOR_PASSWORD"));
        encryptor.setAlgorithm("PBEWithSHA1AndDESEDE");

        final Properties properties = new EncryptableProperties(encryptor);

        try {
            final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
        } catch (final FileNotFoundException ex) {
            log.error("Failed to find the configuration file.");
            ex.printStackTrace();
        } catch (final IOException ex) {
            log.error("Failed to load/parse the configuration file.");
            ex.printStackTrace();
        }
        return properties;
    }

    @Override
    protected void configure() {
        Names.bindProperties(binder(), applicationProperties());
    }
}
