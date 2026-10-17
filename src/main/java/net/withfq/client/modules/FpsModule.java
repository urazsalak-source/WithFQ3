package net.withfq.client.modules;

import net.minecraft.client.gui.GuiGraphics;

public class FpsModule extends HudModule {
    public FpsModule() {
        super("FPS", "FPS sayacini gosterir.", 4, 20);
    }

    @Override
    protected void draw(GuiGraphics g, int x, int y) {
        drawBox(g, x, y, mc.getFps() + " FPS");
    }
}
