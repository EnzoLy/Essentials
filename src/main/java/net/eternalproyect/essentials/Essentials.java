package net.eternalproyect.essentials;

import co.aikar.commands.PaperCommandManager;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.qrakn.phoenix.lang.file.language.LanguageConfigurationFile;
import com.qrakn.phoenix.lang.file.type.BasicConfigurationFile;
import net.eternalproyect.essentials.chat.ChatListener;
import net.eternalproyect.essentials.commands.*;
import net.eternalproyect.essentials.listeners.PlayerListeners;
import net.eternalproyect.essentials.profile.ProfileListeners;
import net.eternalproyect.essentials.util.menu.MenuListener;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public class Essentials extends JavaPlugin {

    public static final Gson GSON = new Gson();
    public static final Type LIST_STRING_TYPE = new TypeToken<List<String>>() {}.getType();

    @Getter private static Essentials instance;

    private MongoDatabase mongoDatabase;
    private BasicConfigurationFile configuration;
    private LanguageConfigurationFile lang;
    private Chat vchat;
    private net.eternalproyect.essentials.chat.Chat chat;
    private PaperCommandManager manager;

    @Override
    public void onEnable() {
        instance = this;
        configuration = new BasicConfigurationFile(this, "config");
        lang = new LanguageConfigurationFile(this, "lang");

        loadMongo();

        RegisteredServiceProvider<Chat> chatProvider = this.getServer().getServicesManager().getRegistration(Chat.class);
        if(chatProvider != null){
            vchat = chatProvider.getProvider();
        }

        chat = new net.eternalproyect.essentials.chat.Chat();

        manager = new PaperCommandManager(this);

        Arrays.asList(
                new ChatListener(),
                new ProfileListeners(),
                new MenuListener(),
                new PlayerListeners()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));

        new RegisterCommands();
    }

    private void loadMongo() {
        if (configuration.getBoolean("mongo.authentication.enabled")) {
            ServerAddress serverAddress = new ServerAddress(configuration.getString("mongo.host"),
                    configuration.getInteger("mongo.port"));

            MongoCredential credential = MongoCredential.createCredential(
                    configuration.getString("mongo.authentication.username"), "admin",
                    configuration.getString("mongo.authentication.password").toCharArray());

            mongoDatabase = new MongoClient(serverAddress, credential, MongoClientOptions.builder().build())
                    .getDatabase("instance");
        } else {
            mongoDatabase = new MongoClient(configuration.getString("mongo.host"),
                    configuration.getInteger("mongo.port")).getDatabase("instance");
        }
    }
}
