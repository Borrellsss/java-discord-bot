package io.edoardofilippo.borrello;

import lombok.ToString;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Bot {
    private static Bot instance;
    private JDA jda;
    private List<ListenerAdapter> listeners = new ArrayList<>();

    private Bot() {}

    private Bot(JDA jda, List<ListenerAdapter> listeners) {
        this.jda = jda;
        this.listeners = listeners;
    }

    public static Bot getOrCreate(JDA jda, List<ListenerAdapter> listeners) {
        if (instance == null) return new Bot(jda, listeners);
        else return instance;
    }

    static class Builder {
        private JDA jda;
        private List<ListenerAdapter> listeners = new ArrayList<>();

        public Builder setJDA(String token, List<GatewayIntent> gatewayIntents) {
            jda = JDABuilder.createDefault(token, gatewayIntents).build();
            return this;
        }
        public Builder setListeners(List<ListenerAdapter> listeners) {
            this.listeners = listeners;
            return this;
        }
        public Bot build() {
            return Bot.getOrCreate(this.jda, this.listeners);
        }
    }
}
