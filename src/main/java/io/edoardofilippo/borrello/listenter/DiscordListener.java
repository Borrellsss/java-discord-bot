package io.edoardofilippo.borrello.listenter;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiscordListener extends ListenerAdapter {
    private final Logger logger = LoggerFactory.getLogger(DiscordListener.class);
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        logger.debug(content);
        if (content.equals("HI!")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Fuck off!").queue();
        }
    }
}
