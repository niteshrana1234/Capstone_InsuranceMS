package com.takeo.config;

import com.takeo.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


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
    public GatewayFilter apply(Object config)
    {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(org.springframework.http.HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try {
                    //validate token
                    LOGGER.info(" authHeader ??method is called.");
                    jwtUtil.validateToken(authHeader);

                    //Get role from token assuming you are storing role in claims with key 'role'
                    String role = jwtUtil.getClaimFromToken(authHeader, "role");

                    // Check if request path starts with /admin/api or /user/api
                    String path = exchange.getRequest().getPath().value();
                    LOGGER.info("role="+role+"===>get value  ??method is called.");

                    if(path.startsWith("/admin/api/") && !role.contains("ROLE_ADMIN")){
                        LOGGER.info(" admin Role ??method is called.");
                        throw new RuntimeException("You don't have enough permissions to access this resource");
                    }
                    else if(path.startsWith("/user/api/") && !role.contains("ROLE_USER")){
                        LOGGER.info(" User Role ??method is called.");
                        throw new RuntimeException("You don't have enough permissions to access this resource");

                    }

                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    LOGGER.info(" unauthorized  ??method is called.");

                    throw new RuntimeException("unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
