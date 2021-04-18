package com.example.redisblog;

import com.example.redisblog.dto.TestDto;
import com.example.redisblog.util.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    private static final int REDIS_RESERVE_TTL_SECOND = 3600;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    @DisplayName("redis 저장 및 조회 - json")
    void stringValueTest() throws JsonProcessingException {
        String redisKey = "test";
        TestDto testData = TestDto.builder()
            .id(1L)
            .test("test")
            .test1("test1")
            .build();

        redisTemplate.opsForValue().set(redisKey, JsonHelper.toJSONString(testData), REDIS_RESERVE_TTL_SECOND, TimeUnit.SECONDS);

        Object savedData = redisTemplate.opsForValue().get(redisKey);
        TestDto testDto = JsonHelper.convertJSONStringToClass(savedData.toString(), TestDto.class);

        Assertions.assertThat(testDto.getId()).isEqualTo(1L);
        Assertions.assertThat(testDto.getTest()).isEqualTo("test");
        Assertions.assertThat(testDto.getTest1()).isEqualTo("test1");
    }
}
