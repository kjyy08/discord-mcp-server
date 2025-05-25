package dev.luigi.discord.mcp.server.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MessageHistoryRequestDto(
        String channelId, // 메시지 기록을 조회할 채널 ID
        int limit, // 조회할 메시지 개수 제한
        String before, // 특정 메시지 이전 메시지 조회
        String after // 특정 메시지 이후 메시지 조회
) {
}
