package net.withfq.client.modules;

import net.minecraft.client.gui.GuiGraphics;

public class CoordsModule extends HudModule {
    public CoordsModule() {
        super("Coords", "Koordinatlari ve bakis yonunu gosterir.", 4, 36);
    }

    @Override
    protected void draw(GuiGraphics g, int x, int y) {
        if (mc.player == null) return;
        String text = String.format("XYZ: %d %d %d  [%s]",
                (int) Math.floor(mc.player.getX()),
                (int) Math.floor(mc.player.getY()),
                (int) Math.floor(mc.player.getZ()),
                mc.player.getDirection().getName());
        drawBox(g, x, y, text);
    }
}
