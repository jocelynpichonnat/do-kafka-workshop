package com.lafabriquedigitowl.training.utils;

import java.util.Properties;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;

public class CommonConfig {

  private CommonConfig() {
    //never be used
  }

  //Security
  public static final String SASL_USERNAME = ""; //TODO replace by the one given by the instructor
  public static final String SASL_PASSWORD = ""; //TODO replace by the one given by the instructor
  public static final String JAAS_TEMPLATE = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
  public static final String SASL_JAAS_CONFIG = String.format(JAAS_TEMPLATE, SASL_USERNAME,
      SASL_PASSWORD);
  public static final String SECURITY_PROTOCOL = "SASL_SSL";
  public static final String SASL_MECANISM = "SCRAM-SHA-256";
  public static final String SSL_TRUSTSTORE_TYPE = "jks";
  public static final String SSL_TRUSTSTORE_LOCATION = ""; //TODO replace by the file you generated earlier
  public static final String SSL_TRUSTSTORE_PASSWORD = ""; //TODO replace by the one you choose earlier

  //Bootstrap
  public static final String BOOTSTRAP_SERVER = ""; //TODO replace by the one given by the instructor

  public static Properties commonProperties() {
    Properties properties = new Properties();
    properties.setProperty(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG,
        CommonConfig.BOOTSTRAP_SERVER);
    properties.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
        CommonConfig.SECURITY_PROTOCOL);
    properties.setProperty(SaslConfigs.SASL_MECHANISM, CommonConfig.SASL_MECANISM);
    properties.setProperty(SaslConfigs.SASL_JAAS_CONFIG, CommonConfig.SASL_JAAS_CONFIG);
    properties.setProperty(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG,
        CommonConfig.SSL_TRUSTSTORE_TYPE);
    properties.setProperty(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
        CommonConfig.SSL_TRUSTSTORE_LOCATION);
    properties.setProperty(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,
        CommonConfig.SSL_TRUSTSTORE_PASSWORD);
    return properties;
  }

}
