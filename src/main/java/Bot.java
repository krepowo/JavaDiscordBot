
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, InterruptedException {
        CommandClientBuilder builder = new CommandClientBuilder().addSlashCommand(new Ping()).addSlashCommand(new Hug()).setOwnerId("620393180168323082")
                .forceGuildOnly("892336997488816128").setActivity(Activity.competing("Java"));
        CommandClient commandClient = builder.build();

        JDA client = JDABuilder.createDefault("OTc0ODY3MzQzNzIzMzQ3OTc4.GjQiU5.kGE8lnlgiPcQkvhrfceUkkl7KHSOlHl7-c0HfM")
                .addEventListeners(commandClient)
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.competing("Java")).build().awaitReady();
    }
}
