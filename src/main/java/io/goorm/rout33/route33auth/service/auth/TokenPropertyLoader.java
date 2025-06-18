package io.goorm.rout33.route33auth.service.auth;

import org.springframework.stereotype.Component;


@Component
public class TokenPropertyLoader {

    public TokenProperty load() {
        try {
            String secretKey = "cm91dGUzMy10b2tlbi1yb3V0ZTMzLXRva2VuLXJvdXRlMzM=";
            long access = 3600000L;
            long refresh = 1209600000L;

            return new TokenProperty(secretKey, access, refresh);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load token properties from Secrets Manager", e);
        }
    }
}
