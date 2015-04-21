package io.inkstand.examples.helloworld;

import org.apache.deltaspike.core.api.config.PropertyFileConfig;

/**
 * This configuration provides a pointer to the properties file that contains the configuration properties
 * for setting up the inkstand service.
 * The pointer is optional, without providing a property file, the default settings will be used, which are
 * <ul>
 *     <li>default http port: 80</li>
 *     <li>default http listen address: localhost</li>
 * </ul>
 *
 * Created by <a href="mailto:gerald.muecke@gmail.com">Gerald M&uuml;cke</a> on 4/21/2015
 *
 * @author <a href="mailto:gerald.muecke@gmail.com">Gerald M&uuml;cke</a>
 */
public class HelloWorldConfigProperties implements PropertyFileConfig {

    public String getPropertyFileName() {

        return "helloWorld.properties";
    }
}
