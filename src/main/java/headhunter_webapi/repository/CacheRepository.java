package headhunter_webapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
public class CacheRepository  {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate _redisTemplate;


    public <T> void save(String objectTitle, String key, T value, Duration duration){
        _redisTemplate.opsForValue().set(objectTitle+":"+key, value);
    }
    public <T> Optional<T> getData(String objectTitle, String key){
        return Optional.ofNullable((T) _redisTemplate.opsForValue().get(objectTitle+":"+key));

    }
}