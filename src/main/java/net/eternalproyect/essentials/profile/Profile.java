package net.eternalproyect.essentials.profile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.qrakn.phoenix.lang.file.language.LanguageConfigurationFileLocale;
import lombok.Getter;
import lombok.Setter;
import net.eternalproyect.essentials.Essentials;
import net.eternalproyect.essentials.EssentialsAPI;
import net.eternalproyect.essentials.profile.conversation.ProfileConversations;
import net.eternalproyect.essentials.profile.option.ProfileOptions;
import net.eternalproyect.essentials.util.Cooldown;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class Profile {

    @Getter
    private static Map<UUID, Profile> profiles = Maps.newHashMap();

    private static MongoCollection<Document> collection = Essentials.getInstance().getMongoDatabase().getCollection("profiles");

    private UUID uuid;
    @Setter private String name;
    @Getter private List<String> ipAddresses;
    @Setter private Long firstSeen;
    @Setter private Long lastLogin;
    @Setter private Double balance;
    @Setter private String currentAddress;
    @Setter private Location lastLocation;
    private final ProfileOptions options;
    @Setter private Cooldown chatCooldown;
    private final ProfileConversations conversations;
    @Setter private String locale;
    private boolean loaded;
    @Setter
    private boolean spy;
    @Setter
    private ChatColor color = ChatColor.WHITE;
    //private List<Punishment> punishments;

    public Profile(String name, UUID uuid){
        this.uuid = uuid;
        this.name = name;
        this.ipAddresses = Lists.newArrayList();
        this.options = new ProfileOptions();
        this.conversations = new ProfileConversations(this);
        this.chatCooldown = new Cooldown(0);

        load();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void addBalance(double balance){
        this.balance += balance;
    }
    public void subtractBalance(double balance){
        this.balance -= balance;
    }

    public static Profile getByUuid(UUID uuid) {
        if (profiles.containsKey(uuid)) {
            return profiles.get(uuid);
        }

        return new Profile(null, uuid);
    }

    public static Profile getByUsername(String username) {
        Player player = Bukkit.getPlayer(username);

        if (player != null) {
            return profiles.get(player.getUniqueId());
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(username);

        if (offlinePlayer.hasPlayedBefore()) {
            if (profiles.containsKey(offlinePlayer.getUniqueId())) {
                return profiles.get(offlinePlayer.getUniqueId());
            }

            return new Profile(offlinePlayer.getName(), offlinePlayer.getUniqueId());
        }

        return null;
    }

    public LanguageConfigurationFileLocale getLocale(){
        return LanguageConfigurationFileLocale.getByName(locale);
    }

    public void load() {
        Document document = collection.find(Filters.eq("uuid", uuid.toString())).first();

        if (document != null) {

            if (name == null) name = document.getString("username");

            firstSeen = document.getLong("firstSeen");
            locale = document.getString("locale");
            lastLogin = document.getLong("lastSeen");
            balance = document.getDouble("balance");
            currentAddress = document.getString("currentAddress");
            ipAddresses = Essentials.GSON.fromJson(document.getString("ipAddresses"), Essentials.LIST_STRING_TYPE);

            Document optionsDocument = (Document) document.get("options");
            options.publicChatEnabled(optionsDocument.getBoolean("publicChatEnabled"));
            options.receivingNewConversations(optionsDocument.getBoolean("receivingNewConversations"));
            options.playingMessageSounds(optionsDocument.getBoolean("playingMessageSounds"));
        }
		loaded = true;
    }

    public void save() {
        Document document = new Document();
        document.put("username", name);
        document.put("uuid", uuid.toString());
        document.put("firstSeen", firstSeen);
        document.put("lastSeen", lastLogin);
        document.put("currentAddress", currentAddress);
        document.put("locale", locale);
        document.put("balance", balance);
        document.put("ipAddresses", Essentials.GSON.toJson(ipAddresses, Essentials.LIST_STRING_TYPE));

        Document optionsDocument = new Document();
        optionsDocument.put("publicChatEnabled", options.publicChatEnabled());
        optionsDocument.put("receivingNewConversations", options.receivingNewConversations());
        optionsDocument.put("playingMessageSounds", options.playingMessageSounds());
        document.put("options", optionsDocument);

        collection.replaceOne(Filters.eq("uuid", uuid.toString()), document, new ReplaceOptions().upsert(true));
    }
}
