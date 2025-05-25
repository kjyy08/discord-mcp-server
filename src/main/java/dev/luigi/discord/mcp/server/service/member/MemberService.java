package dev.luigi.discord.mcp.server.service.member;

import dev.luigi.discord.mcp.server.dto.response.MemberResponseDto;

import java.util.List;

public interface MemberService {
    MemberResponseDto getMemberById(String memberId); // 특정 멤버의 정보를 조회합니다.

    List<MemberResponseDto> getMembersByGuildId(String guildId); // 서버의 모든 멤버 목록을 조회합니다.

    List<MemberResponseDto> getMembersByChannelId(String channelId); // 특정 채널의 멤버 목록을 조회합니다.
}
