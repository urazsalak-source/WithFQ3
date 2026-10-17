package net.withfq.client.modules;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.GuiGraphics;

public class KeyStrokesModule extends HudModule {
    private static final int SIZE = 20;
    private static final int GAP = 2;

    public KeyStrokesModule() {
        super("KeyStrokes", "WASD tuslarini ekranda gosterir.", 4, 120);
    }

    @Override
    protected void draw(GuiGraphics g, int x, int y) {
        int step = SIZE + GAP;
        key(g, x + step, y, "W", mc.options.keyUp);
        key(g, x, y + step, "A", mc.options.keyLeft);
        key(g, x + step, y + step, "S", mc.options.keyDown);
        key(g, x + step * 2, y + step, "D", mc.options.keyRight);
    }

    private void key(GuiGraphics g, int x, int y, String label, KeyMapping mapping) {
        boolean down = mapping.isDown();
        g.fill(x, y, x + SIZE, y + SIZE, down ? 0xCC8A5CFF : BG);
        int tx = x + (SIZE - mc.font.width(label)) / 2;
        g.drawString(mc.font, label, tx, y + 6, WHITE, true);
    }
}
