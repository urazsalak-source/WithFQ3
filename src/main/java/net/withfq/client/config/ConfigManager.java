package net.withfq.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;
import net.withfq.client.WithFQClient;
import net.withfq.client.core.Module;
import net.withfq.client.core.ModuleManager;
import net.withfq.client.setting.Setting;

public final class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("withfq.json");

    private ConfigManager() {}

    public static void save(ModuleManager manager) {
        JsonObject root = new JsonObject();
        for (Module m : manager.all()) {
            JsonObject o = new JsonObject();
            if (m.persistent()) o.addProperty("enabled", m.isEnabled());
            JsonObject s = new JsonObject();
            for (Setting setting : m.settings) s.add(setting.name, setting.toJson());
            o.add("settings", s);
            root.add(m.id(), o);
        }
        try {
            Files.writeString(FILE, GSON.toJson(root));
        } catch (IOException e) {
            WithFQClient.LOGGER.error("Config kaydedilemedi", e);
        }
    }

    public static void load(ModuleManager manager) {
        if (!Files.exists(FILE)) return;
        try {
            JsonObject root = JsonParser.parseString(Files.readString(FILE)).getAsJsonObject();
            for (Module m : manager.all()) {
                if (!root.has(m.id())) continue;
                JsonObject o = root.getAsJsonObject(m.id());
                if (o.has("settings")) {
                    JsonObject s = o.getAsJsonObject("settings");
                    for (Setting setting : m.settings) setting.fromJson(s.get(setting.name));
                }
                if (m.persistent() && o.has("enabled")) m.setEnabled(o.get("enabled").getAsBoolean());
            }
        } catch (Exception e) {
            WithFQClient.LOGGER.error("Config okunamadi", e);
        }
    }
}
