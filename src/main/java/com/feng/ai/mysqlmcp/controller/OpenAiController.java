package com.feng.ai.mysqlmcp.controller;

import com.feng.ai.mysqlmcp.service.ChatClientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Slf4j
public class OpenAiController {

    @Resource
    private ChatClientService chatClientService;

    record ChatRequest(String message) {
    }

    @CrossOrigin
    @PostMapping(value = "/api/ai-test/chat")
    public String generateAsString(@RequestBody ChatRequest chatRequest) {
        return chatClientService.generateAsString(chatRequest.message);
    }
}
