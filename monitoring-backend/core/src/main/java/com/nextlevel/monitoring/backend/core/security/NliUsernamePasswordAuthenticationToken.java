package com.nextlevel.monitoring.backend.core.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class NliUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    /**
     *
     */
    private static final long serialVersionUID = 2640372624357360687L;

    private String clientId;
    private String userName;

    String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public NliUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public NliUsernamePasswordAuthenticationToken(User principal, String token, Collection<? extends GrantedAuthority> authorities) {
        super(principal, token, authorities);
    }


    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (this.clientId != null) {
            hash ^= this.clientId.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !clientId.equals(((NliUsernamePasswordAuthenticationToken) obj).clientId)) {
            return false;
        }
        return super.equals(obj);
    }
}
