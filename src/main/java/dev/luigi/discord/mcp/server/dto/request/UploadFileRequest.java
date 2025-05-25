package dev.luigi.discord.mcp.server.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UploadFileRequest(
        String filename, // 업로드할 파일명
        String description, // 파일 설명
        byte[] data, // 파일 데이터
        String channelId // 파일을 업로드할 채널 ID
) {
}
