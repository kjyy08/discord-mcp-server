package dev.luigi.discord.mcp.server.dto.response;

import java.time.LocalDateTime;

public record FileResponseDto(
        String id, // 파일 ID
        String filename, // 파일명
        String url, // 파일 URL
        Long size, // 파일 크기
        String contentType, // 파일 컨텐츠 타입
        String channelId, // 파일이 업로드된 채널 ID
        String uploaderId, // 파일을 업로드한 사용자 ID
        LocalDateTime uploadedAt // 파일 업로드 시간
) {
}
