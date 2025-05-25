package dev.luigi.discord.mcp.server.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MemberResponseDto(
        String id, // 멤버 ID
        String username, // 멤버 사용자명
        String nickname // 멤버 닉네임
) {
}
