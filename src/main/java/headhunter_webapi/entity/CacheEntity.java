package headhunter_webapi.entity;

public enum CacheEntity {
    REFRESH_TOKEN("refresh_token"),
    SECRET_CODE("secret_code");

    private String _storedEntity;
    CacheEntity(String storedEntity) {
        _storedEntity=storedEntity;
    }
    @Override
    public String toString(){
        return  _storedEntity;
    }
}
