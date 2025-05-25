package dev.luigi.discord.mcp.server.service.channel;

import dev.luigi.discord.mcp.server.dto.response.ChannelResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscordChannelService implements ChannelService {
    private final JDA jda;

    @Override
    public List<ChannelResponseDto> getChannels(String serverId) {
        try {
            return Objects.requireNonNull(jda.getGuildById(serverId))
                    .getTextChannels()
                    .stream()
                    .map(channel -> ChannelResponseDto.builder()
                            .id(channel.getId())
                            .name(channel.getName())
                            .type("text")
                            .topic(channel.getTopic())
                            .build())
                    .toList();
        } catch (Exception e) {
            log.error("Error fetching channels for server ID {}: {}", serverId, e.getMessage());
            throw new RuntimeException("Failed to fetch channels for server ID: " + serverId, e);
        }
    }

    @Override
    public ChannelResponseDto getChannelByName(String serverId, String channelName) {
        try {
            return Objects.requireNonNull(jda.getGuildById(serverId))
                    .getTextChannelsByName(channelName, true)
                    .stream()
                    .findFirst()
                    .map(channel -> ChannelResponseDto.builder()
                            .id(channel.getId())
                            .name(channel.getName())
                            .type(channel.getType().name())
                            .topic(channel.getTopic())
                            .build())
                    .orElseThrow(() -> new RuntimeException("Channel not found: " + channelName));
        } catch (Exception e) {
            log.error("Error fetching channel ID for server ID {} and channel name {}: {}", serverId, channelName, e.getMessage());
            throw new RuntimeException("Failed to fetch channel ID for server ID: " + serverId + " and channel name: " + channelName, e);
        }
    }

    @Override
    public ChannelResponseDto getChannelById(String serverId, String channelId) {
        try {
            TextChannel channel = Objects.requireNonNull(jda.getGuildById(serverId))
                    .getTextChannelById(channelId);

            if (channel == null) {
                throw new RuntimeException("Channel not found with ID: " + channelId);
            }

            return ChannelResponseDto.builder()
                    .id(channel.getId())
                    .name(channel.getName())
                    .type(channel.getType().name())
                    .topic(channel.getTopic())
                    .build();
        } catch (Exception e) {
            log.error("Error fetching channel name for server ID {} and channel ID {}: {}", serverId, channelId, e.getMessage());
            throw new RuntimeException("Failed to fetch channel name for server ID: " + serverId + " and channel ID: " + channelId, e);
        }
    }
}
