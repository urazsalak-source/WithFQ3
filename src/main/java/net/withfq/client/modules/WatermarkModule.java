package net.withfq.client.modules;

import net.minecraft.client.gui.GuiGraphics;
import net.withfq.client.WithFQClient;

public class WatermarkModule extends HudModule {
    public WatermarkModule() {
        super("Watermark", "Ekranda WithFQ yazisi gosterir.", 4, 4);
        setEnabled(true);
    }

    @Override
    protected void draw(GuiGraphics g, int x, int y) {
        drawBox(g, x, y, WithFQClient.NAME + " " + WithFQClient.VERSION);
    }
}
