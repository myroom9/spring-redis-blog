package com.example.redisblog.controller;

import com.example.redisblog.dto.TestDto;
import com.example.redisblog.repository.TestRepository;
import com.example.redisblog.util.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class TestController {
    private final TestRepository testRepository;

    @PostMapping(value =  "")
    public String saveUserInfo(TestDto testDto) throws JsonProcessingException {
        testRepository.saveUserInfo(testDto);
        return "success";
    }

    @Cacheable(value = "getUserInfo", key = "#id", cacheManager = "cacheManager")
    @GetMapping(value = "/{id}")
    public String getUserInfo(@PathVariable("id") Long id) {
        return testRepository.getUserInfo(id);
    }
}
