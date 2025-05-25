package dev.luigi.discord.mcp.server.service.file;

import dev.luigi.discord.mcp.server.dto.request.UploadFileRequest;
import dev.luigi.discord.mcp.server.dto.response.FileResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.utils.FileUpload;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscordFileService implements FileService {
    private final JDA jda;

    @Override
    public FileResponseDto uploadFile(UploadFileRequest request) {
        try (FileUpload fileUpload = FileUpload.fromData(request.data(), request.filename())) {
            return Objects.requireNonNull(jda.getTextChannelById(request.channelId()))
                    .sendFiles(fileUpload)
                    .setContent(request.description())
                    .complete()
                    .getAttachments()
                    .stream()
                    .findFirst()
                    .map(attachment -> FileResponseDto.builder()
                            .id(attachment.getId())
                            .filename(attachment.getFileName())
                            .url(attachment.getUrl())
                            .size(attachment.getSize())
                            .contentType(attachment.getContentType())
                            .build())
                    .orElseThrow(() -> new RuntimeException("Failed to upload file, no attachment returned"));
        } catch (Exception e) {
            log.error("Error uploading file: {}", e.getMessage());
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}
