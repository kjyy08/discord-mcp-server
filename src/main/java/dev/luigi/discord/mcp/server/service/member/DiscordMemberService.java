package dev.luigi.discord.mcp.server.service.member;

import dev.luigi.discord.mcp.server.dto.response.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscordMemberService implements MemberService {
    private final JDA jda;

    @Override
    public MemberResponseDto getMemberById(String memberId) {
        try {
            User user = Objects.requireNonNull(jda.getUserById(memberId));

            return MemberResponseDto.builder()
                    .id(user.getId())
                    .username(user.getName())
                    .nickname(user.getEffectiveName())
                    .build();

        } catch (Exception e) {
            log.error("Error fetching member by ID: {}", memberId, e);
            throw new RuntimeException("Failed to fetch member by ID: " + memberId, e);
        }
    }

    @Override
    public List<MemberResponseDto> getMembersByGuildId(String guildId) {
        try {
            return Objects.requireNonNull(jda.getGuildById(guildId)).getMembers().stream()
                    .map(member -> MemberResponseDto.builder()
                            .id(member.getId())
                            .username(member.getUser().getName())
                            .nickname(member.getEffectiveName())
                            .build())
                    .toList();
        } catch (Exception e) {
            log.error("Error fetching members by guild ID: {}", guildId, e);
            throw new RuntimeException("Failed to fetch members by guild ID: " + guildId, e);
        }
    }

    @Override
    public List<MemberResponseDto> getMembersByChannelId(String channelId) {
        try {
            List<Member> members = Objects.requireNonNull(jda.getTextChannelById(channelId)).getMembers();

            return Objects.requireNonNull(jda.getTextChannelById(channelId)).getMembers().stream()
                    .map(member -> MemberResponseDto.builder()
                            .id(member.getId())
                            .username(member.getUser().getName())
                            .nickname(member.getEffectiveName())
                            .build())
                    .toList();
        } catch (Exception e) {
            log.error("Error fetching members by channel ID: {}", channelId, e);
            throw new RuntimeException("Failed to fetch members by channel ID: " + channelId, e);
        }
    }
}
