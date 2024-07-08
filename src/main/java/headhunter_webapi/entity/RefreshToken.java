package headhunter_webapi.entity;

import jakarta.persistence.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;
@RedisHash(value="refreshToken")
public class RefreshToken {
    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user_email;
    private String token;
    private Date issuedAt;
    private Date expiresAt;

    public String getToken() {
        return token;
    }

    public User getUser_email() {
        return user_email;
    }

    public RefreshToken(User user_email, String token, Date issuedAt, Date expiresAt) {
        this.user_email = user_email;
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

    public void setUser_email(User user_email) {
        this.user_email = user_email;
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

    public RefreshToken(){

    }
}
