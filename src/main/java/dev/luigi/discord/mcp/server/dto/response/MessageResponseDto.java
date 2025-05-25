package dev.luigi.discord.mcp.server.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MessageResponseDto(
        String id, // 메시지 ID
        String content, // 메시지 내용
        String authorId, // 메시지 작성자 ID
        String authorName, // 메시지 작성자 이름
        String nickname, // 메시지 작성자 닉네임
        String channelId, // 메시지가 속한 채널 ID
        LocalDateTime timestamp // 메시지 전송 시간
) {
}
