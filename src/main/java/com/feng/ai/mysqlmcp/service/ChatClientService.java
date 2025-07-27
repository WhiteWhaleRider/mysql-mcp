package com.feng.ai.mysqlmcp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatClientService {
    private final ChatClient chatClient;
    private final String prompt = """
           查询名字叫若依的用户信息
           """;

    public ChatClientService(ChatClient.Builder builder, SyncMcpToolCallbackProvider mcpToolProvider) {
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultSystem(prompt)
                .defaultToolCallbacks(mcpToolProvider).build();
    }

    public String generateAsString(String message) {
        String content = this.chatClient.prompt()
                .user(promptUserSpec -> promptUserSpec.text(message))
                .call().content();
        log.info("大模型回答：{}", content);
        return content;
    }
}
