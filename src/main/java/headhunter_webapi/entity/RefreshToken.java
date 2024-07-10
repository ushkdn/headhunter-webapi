package headhunter_webapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
public class RefreshToken implements Serializable {
    @JsonProperty("token")
    private String token;
    @JsonProperty("issuedAt")
    private Date issuedAt;
    @JsonProperty("expiresAt")
    private Date expiresAt;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public RefreshToken( String token, Date issuedAt, Date expiresAt) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }
    public RefreshToken(){

    }
}
