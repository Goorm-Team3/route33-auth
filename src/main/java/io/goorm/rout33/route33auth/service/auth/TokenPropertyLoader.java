package io.goorm.rout33.route33auth.service.auth;

import io.goorm.rout33.route33auth.service.auth.TokenProperty;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

import java.io.StringReader;
import java.util.Properties;

@Component
public class TokenPropertyLoader {

    private static final String SECRET_NAME = "spring/backend/token-config";
    private final SecretsManagerClient client;

    public TokenPropertyLoader() {
        this.client = SecretsManagerClient.builder()
                .region(Region.of("ap-northeast-2"))
                .build();
    }

    public TokenProperty load() {
//        SecretsManagerClient client = SecretsManagerClient.builder()
//                .region(Region.of("ap-northeast-2"))
//                .build();

        String secretString = client.getSecretValue(GetSecretValueRequest.builder()
                        .secretId(SECRET_NAME)
                        .build())
                .secretString();

        try {
            Properties props = new Properties();
            props.load(new StringReader(secretString));

            String secretKey = props.getProperty("token.secret-key");
            long access = Long.parseLong(props.getProperty("token.access-token-expiration-millis"));
            long refresh = Long.parseLong(props.getProperty("token.refreshTokenExpirationMillis"));

            return new TokenProperty(secretKey, access, refresh);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load token properties from Secrets Manager", e);
        }
    }
}
