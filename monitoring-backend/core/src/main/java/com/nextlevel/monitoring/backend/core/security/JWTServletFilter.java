package com.nextlevel.monitoring.backend.core.security;

import com.nextlevel.monitoring.backend.core.common.CommonRuntimeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ConditionalOnProperty(value = "keycloak.enabled", havingValue = "false")
@Component
public class JWTServletFilter extends GenericFilterBean {

    static final String AUTHORIZATION_HEADER = "Authorization";
    private final Logger log = LoggerFactory.getLogger(JWTServletFilter.class);

    @Autowired
    private TokenProvider tokenProvider;

    public JWTServletFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {

            chain.doFilter(request, response);
            MDC.clear();
        } catch (ExpiredJwtException eje) {
            log.error("Authentication failed. Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (SignatureException e) {
            log.error("Authentication failed. Invalid JWT signature: {}", e.getMessage());
            throw e;
        }
    }

    private void checkClientAuthorized(Jws<Claims> claims, String clientId) {
    	if(clientId == null || clientId.trim().isEmpty())
    		return;//null client is considered accepted
        String auth = (String)claims.getBody().get("auth");
        String[] items = (auth == null) ? new String[0]: auth.split(",");
        String clientIdWithUnderline = clientId.trim().replace(" ", "_");
        for(String item: items) {
        	if(!item.startsWith("CLIENT_"))
        		continue;
        	String client = item.substring(7);//after CLIENT_ index
        	if(clientIdWithUnderline.equals(client))//client security is case sensitive
        		return;
        }
        throw new CommonRuntimeException("Client not authorized. client name: "+clientId);
	}

	private void contextLog(String username, String clientId) {
        MDC.put("user", username);
        MDC.put("client", clientId);
	}

	private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

}
