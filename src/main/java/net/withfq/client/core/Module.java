package net.withfq.client.core;

import com.mojang.blaze3d.platform.InputConstants;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.withfq.client.setting.Setting;
import org.lwjgl.glfw.GLFW;

public abstract class Module {
    public final String name;
    public final String description;
    public final Category category;
    public final List<Setting> settings = new ArrayList<>();
    /** Vanilla kontroller menusunden her modul icin tus atanabilir. */
    public final KeyMapping keyMapping;
    public boolean expanded;

    protected final Minecraft mc = Minecraft.getInstance();
    private boolean enabled;

    protected Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.keyMapping = new KeyMapping(
                "key.withfq." + id(),
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KeyMapping.Category.MISC);
    }

    public String id() {
        return name.toLowerCase().replace(" ", "_");
    }

    protected <S extends Setting> S add(S setting) {
        settings.add(setting);
        return setting;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean value) {
        if (this.enabled == value) return;
        this.enabled = value;
        if (value) onEnable(); else onDisable();
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    /** false ise acik/kapali durumu config'e kaydedilmez (ornek: Zoom). */
    public boolean persistent() {
        return true;
    }

    protected void onEnable() {}

    protected void onDisable() {}

    public void onTick() {}

    public void onHud(GuiGraphics g) {}
}
