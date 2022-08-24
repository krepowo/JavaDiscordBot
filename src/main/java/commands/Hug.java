package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Hug extends SlashCommand {
    public Hug() {
        this.name = "hug";
        this.help = "Hug someone :)";

        this.options = Collections.singletonList(new OptionData(OptionType.USER, "target", "The person you want to hug").setRequired(true));
    }

    public void execute(SlashCommandEvent event) {
        event.deferReply().queue();
        URL url = null;
        try {
            url = new URL("https://api.waifu.pics/sfw/hug");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int resscode = conn.getResponseCode();

            if(resscode != 200) {
                event.reply("Failed to fetch image API, please try again later").setEphemeral(true).queue();
            } else {
                StringBuilder infoString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    infoString.append(scanner.nextLine());
                }
                scanner.close();

                JSONParser json = new JSONParser();
                JSONObject dataObj=(JSONObject) json.parse(String.valueOf(infoString));

                String gifURL = (String) dataObj.get("url");


                OptionMapping opt = event.getOption("target");
                String kataAuthor = event.getUser().getName() + " gives " + opt.getAsUser().getName() + " a warm hug!";
                EmbedBuilder embed = new EmbedBuilder().setAuthor(kataAuthor, event.getUser().getAvatarUrl()).setImage(gifURL).setColor(Color.MAGENTA);

                event.getHook().editOriginalEmbeds(embed.build()).queue();
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
