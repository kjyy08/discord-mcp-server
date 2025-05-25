package dev.luigi.discord.mcp.server.service.message;

import dev.luigi.discord.mcp.server.dto.request.MessageHistoryRequestDto;
import dev.luigi.discord.mcp.server.dto.request.SendMessageRequestDto;
import dev.luigi.discord.mcp.server.dto.response.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscordMessageService implements MessageService {
    private final JDA jda;

    @Override
    public MessageResponseDto sendMessage(SendMessageRequestDto req) {
        try {
            Message message = Objects.requireNonNull(jda.getTextChannelById(req.channelId()))
                    .sendMessage(req.content())
                    .complete();

            return MessageResponseDto.builder()
                    .content(message.getContentRaw())
                    .authorId(message.getAuthor().getId())
                    .authorName(message.getAuthor().getName())
                    .nickname(message.getMember().getEffectiveName())
                    .timestamp(message.getTimeCreated().toLocalDateTime())
                    .build();
        } catch (Exception e) {
            log.error("Error sending message to channel {}: {}", req.channelId(), e.getMessage());
            throw new RuntimeException("Failed to send message", e);
        }
    }

    @Override
    public List<MessageResponseDto> getMessageHistory(MessageHistoryRequestDto req) {
        try {
            return Objects.requireNonNull(jda.getTextChannelById(req.channelId()))
                    .getHistory()
                    .retrievePast(req.limit())
                    .complete()
                    .stream()
                    .map(message -> MessageResponseDto.builder()
                            .content(message.getContentRaw())
                            .authorId(message.getAuthor().getId())
                            .authorName(message.getAuthor().getName())
                            .nickname(message.getMember().getEffectiveName())
                            .timestamp(message.getTimeCreated().toLocalDateTime())
                            .build())
                    .toList();
        } catch (Exception e) {
            log.error("Error fetching messages for channel {}: {}", req.channelId(), e.getMessage());
            throw new RuntimeException("Failed to fetch messages", e);
        }
    }
}
