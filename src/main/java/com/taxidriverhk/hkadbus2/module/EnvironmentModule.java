package com.taxidriverhk.hkadbus2.module;

import lombok.extern.log4j.Log4j2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class EnvironmentModule {

    public Properties applicationProperties() {
        final Properties properties = new Properties();
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
}
