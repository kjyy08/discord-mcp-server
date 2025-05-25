package dev.luigi.discord.mcp.server.config;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdaConfig {
    @Value("${discord.token}")
    private String token;

    @Bean
    public JDA jda() throws InterruptedException {
        return JDABuilder
                .createDefault(token,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.MESSAGE_CONTENT)
                .enableCache(CacheFlag.MEMBER_OVERRIDES)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build()
                .awaitReady();
    }
}
