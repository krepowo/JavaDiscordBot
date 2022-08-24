package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;

public class Ping extends SlashCommand {
    public Ping() {
        this.name = "ping";
        this.help = "Displays bots latency to Discord API";
    }

    public void execute(SlashCommandEvent event) {
        event.replyFormat(":ping_pong: Pong! in %s ms", event.getJDA().getGatewayPing()).queue();
    }
}
