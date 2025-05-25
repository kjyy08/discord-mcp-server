package dev.luigi.discord.mcp.server.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FileResponseDto(
        String id, // 파일 ID
        String filename, // 파일명
        String url, // 파일 URL
        int size, // 파일 크기
        String contentType // 파일 컨텐츠 타입
) {
}
