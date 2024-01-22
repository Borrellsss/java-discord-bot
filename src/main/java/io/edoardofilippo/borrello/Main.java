package io.edoardofilippo.borrello;

import io.edoardofilippo.borrello.listenter.DiscordListener;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream(".env")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String token = properties.getProperty("BOT_TOKEN");

            List<GatewayIntent> gatewayIntents = List.of(
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES
            );

            Bot bot = new Bot.Builder()
                .setJDA(token, gatewayIntents)
                .setListeners(List.of(new DiscordListener()))
                .build();

        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
        }
    }
}