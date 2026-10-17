package net.withfq.client.core;

import java.util.ArrayList;
import java.util.List;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.withfq.client.modules.*;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        // HUD
        register(new WatermarkModule());
        register(new FpsModule());
        register(new CoordsModule());
        register(new PingModule());
        register(new KeyStrokesModule());
        // Render
        register(new FullbrightModule());
        register(new ZoomModule());
        register(new ModuleListModule());
        // Player
        register(new ToggleSprintModule());
    }

    private void register(Module m) {
        KeyBindingHelper.registerKeyBinding(m.keyMapping);
        modules.add(m);
    }

    public List<Module> all() {
        return modules;
    }

    public void tick(Minecraft mc) {
        for (Module m : modules) {
            while (m.keyMapping.consumeClick()) {
                if (mc.screen == null) m.toggle();
            }
            if (m.isEnabled() && mc.player != null) m.onTick();
        }
    }

    public void renderHud(GuiGraphics g) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui) return;
        for (Module m : modules) {
            if (m.isEnabled()) m.onHud(g);
        }
    }
}
