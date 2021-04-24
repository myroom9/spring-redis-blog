package com.example.redisblog.repository;

import com.example.redisblog.dto.TestDto;
import com.example.redisblog.util.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TestRepository {

    private static final String REDIS_PREFIX = "TEST";
    private static final int REDIS_RESERVE_TTL_SECOND = 600;

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveUserInfo(TestDto testDto) throws JsonProcessingException {
        redisTemplate.opsForValue().set(REDIS_PREFIX + testDto.getId(), JsonHelper.toJSONString(testDto), REDIS_RESERVE_TTL_SECOND, TimeUnit.SECONDS);
    }

    public String getUserInfo(Long id) {
        log.info("cache 시간 종료로 실제 Repository 조회");
        return redisTemplate.opsForValue().get(REDIS_PREFIX + id).toString();
    }
}
