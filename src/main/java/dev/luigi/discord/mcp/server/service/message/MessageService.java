package dev.luigi.discord.mcp.server.service.message;

import dev.luigi.discord.mcp.server.dto.request.MessageHistoryRequestDto;
import dev.luigi.discord.mcp.server.dto.request.SendMessageRequestDto;
import dev.luigi.discord.mcp.server.dto.response.MessageResponseDto;

import java.util.List;

public interface MessageService {
    MessageResponseDto sendMessage(SendMessageRequestDto req); // 메시지를 전송합니다.

    List<MessageResponseDto> getMessageHistory(MessageHistoryRequestDto req); // 메시지 기록을 조회합니다.
}
