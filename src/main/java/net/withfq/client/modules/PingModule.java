package net.withfq.client.modules;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.PlayerInfo;

public class PingModule extends HudModule {
    public PingModule() {
        super("Ping", "Sunucu gecikmesini gosterir.", 4, 52);
    }

    @Override
    protected void draw(GuiGraphics g, int x, int y) {
        if (mc.player == null || mc.getConnection() == null) return;
        PlayerInfo info = mc.getConnection().getPlayerInfo(mc.player.getUUID());
        int ping = info == null ? 0 : info.getLatency();
        drawBox(g, x, y, ping + " ms");
    }
}
