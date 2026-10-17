package net.withfq.client.modules;

import net.minecraft.client.gui.GuiGraphics;
import net.withfq.client.core.Category;
import net.withfq.client.core.Module;
import net.withfq.client.setting.NumberSetting;

/** Konumu ayarlanabilir HUD modulleri icin taban sinif. */
public abstract class HudModule extends Module {
    public static final int ACCENT = 0xFF8A5CFF;
    public static final int WHITE = 0xFFFFFFFF;
    public static final int BG = 0x99101018;

    protected final NumberSetting posX;
    protected final NumberSetting posY;

    protected HudModule(String name, String description, int defX, int defY) {
        super(name, description, Category.HUD);
        posX = add(new NumberSetting("X", defX, 0, 1000, 1));
        posY = add(new NumberSetting("Y", defY, 0, 600, 1));
    }

    @Override
    public final void onHud(GuiGraphics g) {
        draw(g, posX.getInt(), posY.getInt());
    }

    protected abstract void draw(GuiGraphics g, int x, int y);

    /** Soldaki vurgu cizgisi + yari saydam arka plan ile tek satirlik kutu. */
    protected void drawBox(GuiGraphics g, int x, int y, String text) {
        int w = mc.font.width(text) + 8;
        g.fill(x, y, x + w, y + 12, BG);
        g.fill(x, y, x + 2, y + 12, ACCENT);
        g.drawString(mc.font, text, x + 5, y + 2, WHITE, true);
    }
}
