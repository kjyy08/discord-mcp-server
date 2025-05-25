package dev.luigi.discord.mcp.server.service;

import dev.luigi.discord.mcp.server.dto.response.ChannelResponseDto;
import dev.luigi.discord.mcp.server.dto.response.MemberResponseDto;
import dev.luigi.discord.mcp.server.dto.response.MessageResponseDto;

import java.util.List;

public interface DiscordService {
    List<ChannelResponseDto> getChannels(String serverId); // 특정 서버의 모든 채널 목록을 조회합니다.

    ChannelResponseDto getChannelByName(String serverId, String channelName); // 특정 서버에서 채널 이름으로 채널 정보를 조회합니다.

    ChannelResponseDto getChannelById(String serverId, String channelId); // 특정 서버에서 채널 ID로 채널 정보를 조회합니다.

    MessageResponseDto sendMessage(String channelId, String content); // 메시지를 전송합니다.

    List<MessageResponseDto> getMessageHistory(String channelId, int limit,
                                               String beforeMessageId, String afterMessageId); // 메시지 기록을 조회합니다.

    MemberResponseDto getMemberById(String memberId); // 특정 멤버의 정보를 조회합니다.

    List<MemberResponseDto> getMembersByGuildId(String guildId); // 서버의 모든 멤버 목록을 조회합니다.

    List<MemberResponseDto> getMembersByChannelId(String channelId); // 특정 채널의 멤버 목록을 조회합니다.
}
