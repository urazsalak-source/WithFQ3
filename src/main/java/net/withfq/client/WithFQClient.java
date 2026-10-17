package net.withfq.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyMapping;
import net.withfq.client.config.ConfigManager;
import net.withfq.client.core.ModuleManager;
import net.withfq.client.gui.ClickGuiScreen;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WithFQClient implements ClientModInitializer {
    public static final String NAME = "WithFQ";
    public static final String VERSION = "0.1.0";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static ModuleManager modules;
    private static KeyMapping openGuiKey;

    @Override
    public void onInitializeClient() {
        modules = new ModuleManager();

        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.withfq.open_gui",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                KeyMapping.Category.MISC));

        ConfigManager.load(modules);

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            while (openGuiKey.consumeClick()) {
                if (mc.screen == null) mc.setScreen(new ClickGuiScreen());
            }
            modules.tick(mc);
        });

        HudRenderCallback.EVENT.register((graphics, tickCounter) -> modules.renderHud(graphics));

        ClientLifecycleEvents.CLIENT_STOPPING.register(mc -> ConfigManager.save(modules));

        LOGGER.info("{} {} yuklendi", NAME, VERSION);
    }
}
