package com.takeo.config;

import com.takeo.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<Object> {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationFilter.class);
    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Object.class);
    }

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
//                    //REST call to AUTH service
//                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                    jwtUtil.validateToken(authHeader);


                    String role = jwtUtil.getClaimFromToken(authHeader, "role");
                    String path = exchange.getRequest().getPath().value();

                    if (path.startsWith("/admin/api") && !role.contains("ROLE_ADMIN")) {
                        throw new RuntimeException("You don't have enough permissions to access this resource");
                    } else if (path.startsWith("/user/api") && !role.contains("ROLE_USER")) {
                        throw new RuntimeException("You don't have enough permissions to access this resource");
                    }

                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
