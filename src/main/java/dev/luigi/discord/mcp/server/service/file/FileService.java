package dev.luigi.discord.mcp.server.service.file;

import dev.luigi.discord.mcp.server.dto.request.UploadFileRequest;
import dev.luigi.discord.mcp.server.dto.response.FileResponseDto;

public interface FileService {
    FileResponseDto uploadFile(UploadFileRequest request); // 파일을 업로드합니다.
}
