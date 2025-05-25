package dev.luigi.discord.mcp.server.service;

import dev.luigi.discord.mcp.server.dto.request.MessageHistoryRequestDto;
import dev.luigi.discord.mcp.server.dto.request.SendMessageRequestDto;
import dev.luigi.discord.mcp.server.dto.response.ChannelResponseDto;
import dev.luigi.discord.mcp.server.dto.response.MemberResponseDto;
import dev.luigi.discord.mcp.server.dto.response.MessageResponseDto;
import dev.luigi.discord.mcp.server.service.channel.ChannelService;
import dev.luigi.discord.mcp.server.service.file.FileService;
import dev.luigi.discord.mcp.server.service.member.MemberService;
import dev.luigi.discord.mcp.server.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscordServiceImpl implements DiscordService {
    private final MemberService memberService;
    private final ChannelService channelService;
    private final MessageService messageService;
    private final FileService fileService;

    @Value("${discord.server.id}")
    private String serverId;

    @Tool(name = "getChannels", description = "서버의 모든 채널 목록을 조회합니다.")
    @Override
    public List<ChannelResponseDto> getChannels() {
        try {
            return channelService.getChannels(serverId);
        } catch (Exception e) {
            log.error("Error fetching channels for server {}: {}", serverId, e.getMessage());
            throw new RuntimeException("Failed to fetch channels for server: " + serverId, e);
        }
    }

    @Tool(name = "getChannelByName", description = "서버에서 채널 이름으로 채널 정보를 조회합니다.")
    @Override
    public ChannelResponseDto getChannelByName(@ToolParam String channelName) {
        try {
            return channelService.getChannelByName(serverId, channelName);
        } catch (Exception e) {
            log.error("Error fetching channel by name {} for server {}: {}", channelName, serverId, e.getMessage());
            throw new RuntimeException("Failed to fetch channel by name: " + channelName + " for server: " + serverId, e);
        }
    }

    @Tool(name = "getChannelById", description = "서버에서 채널 ID로 채널 정보를 조회합니다.")
    @Override
    public ChannelResponseDto getChannelById(@ToolParam String channelId) {
        try {
            return channelService.getChannelById(serverId, channelId);
        } catch (Exception e) {
            log.error("Error fetching channel by ID {} for server {}: {}", channelId, serverId, e.getMessage());
            throw new RuntimeException("Failed to fetch channel by ID: " + channelId + " for server: " + serverId, e);
        }
    }

    @Tool(name = "sendMessage", description = "특정 채널로 메시지를 전송합니다.")
    @Override
    public MessageResponseDto sendMessage(@ToolParam String channelId,
                                          @ToolParam String content) {
        try {
            SendMessageRequestDto req = SendMessageRequestDto.builder()
                    .channelId(channelId)
                    .content(content)
                    .build();

            return messageService.sendMessage(req);
        } catch (Exception e) {
            log.error("Error sending message to channel {}: {}", channelId, e.getMessage());
            throw new RuntimeException("Failed to send message to channel: " + channelId, e);
        }
    }

    @Tool(name = "getMessageHistory", description = "특정 채널의 메시지 기록을 조회합니다.")
    @Override
    public List<MessageResponseDto> getMessageHistory(@ToolParam String channelId,
                                                      @ToolParam int limit,
                                                      @ToolParam(required = false) String beforeMessageId,
                                                      @ToolParam(required = false) String afterMessageId) {
        try {
            MessageHistoryRequestDto req = MessageHistoryRequestDto.builder()
                    .channelId(channelId)
                    .limit(limit)
                    .before(beforeMessageId)
                    .after(afterMessageId)
                    .build();

            return messageService.getMessageHistory(req);
        } catch (Exception e) {
            log.error("Error fetching message history for channel {}: {}", channelId, e.getMessage());
            throw new RuntimeException("Failed to fetch message history for channel: " + channelId, e);
        }
    }

    @Tool(name = "getMemberById", description = "특정 멤버의 정보를 조회합니다.")
    @Override
    public MemberResponseDto getMemberById(@ToolParam String memberId) {
        try {
            return memberService.getMemberById(memberId);
        } catch (Exception e) {
            log.error("Error fetching member by ID {}: {}", memberId, e.getMessage());
            throw new RuntimeException("Failed to fetch member by ID: " + memberId, e);
        }
    }

    @Tool(name = "getMembersByGuildId", description = "서버의 모든 멤버 목록을 조회합니다.")
    @Override
    public List<MemberResponseDto> getMembersByGuildId() {
        try {
            return memberService.getMembersByGuildId(serverId);
        } catch (Exception e) {
            log.error("Error fetching members for guild {}: {}", serverId, e.getMessage());
            throw new RuntimeException("Failed to fetch members for guild: " + serverId, e);
        }
    }

    @Tool(name = "getMembersByChannelId", description = "특정 채널의 멤버 목록을 조회합니다.")
    @Override
    public List<MemberResponseDto> getMembersByChannelId(@ToolParam String channelId) {
        try {
            return memberService.getMembersByChannelId(channelId);
        } catch (Exception e) {
            log.error("Error fetching members for channel {}: {}", channelId, e.getMessage());
            throw new RuntimeException("Failed to fetch members for channel: " + channelId, e);
        }
    }
}
