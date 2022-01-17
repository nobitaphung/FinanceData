package com.pmkha.stockdata.payload;

import javax.validation.constraints.NotBlank;

public class JwtResponse {
    private String token;
    private String type = "Ambition";

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
