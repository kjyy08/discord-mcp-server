package dev.luigi.discord.mcp.server.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record SendMessageRequestDto(
        String content, // 전송할 메시지 내용
        String channelId // 메시지를 전송할 채널 ID
) {
}
