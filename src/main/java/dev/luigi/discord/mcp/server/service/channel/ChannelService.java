package dev.luigi.discord.mcp.server.service.channel;

import dev.luigi.discord.mcp.server.dto.response.ChannelResponseDto;

import java.util.List;

public interface ChannelService {
    List<ChannelResponseDto> getChannels(String serverId); // 특정 서버의 모든 채널 목록을 조회합니다.

    ChannelResponseDto getChannelByName(String serverId, String channelName); // 특정 서버에서 채널 이름으로 채널 정보를 조회합니다.

    ChannelResponseDto getChannelById(String serverId, String channelId); // 특정 서버에서 채널 ID로 채널 정보를 조회합니다.
}
