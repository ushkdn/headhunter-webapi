package headhunter_webapi.entity;

public enum CacheEntity {
    REFRESH_TOKEN("refresh_token"),
    VERIFY_EMAIL_CODE("verify_email_code"),
    RESET_PASSWORD_CODE("reset_password_code");

    private String _storedEntity;
    CacheEntity(String storedEntity) {
        _storedEntity=storedEntity;
    }
    @Override
    public String toString(){
        return  _storedEntity;
    }
}
