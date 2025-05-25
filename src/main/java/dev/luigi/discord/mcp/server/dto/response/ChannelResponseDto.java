package dev.luigi.discord.mcp.server.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ChannelResponseDto(
        String id, // 채널 ID
        String name, // 채널 이름
        String type, // 채널 타입 (예: text, voice)
        String topic // 채널 주제
) {
}
