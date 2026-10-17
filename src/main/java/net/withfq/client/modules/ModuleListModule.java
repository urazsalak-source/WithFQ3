package net.withfq.client.modules;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.gui.GuiGraphics;
import net.withfq.client.WithFQClient;
import net.withfq.client.core.Category;
import net.withfq.client.core.Module;

/** Acik modulleri sag ustte uzunluga gore siralayarak listeler. */
public class ModuleListModule extends Module {
    public ModuleListModule() {
        super("ModuleList", "Acik modulleri sag ustte listeler.", Category.RENDER);
        setEnabled(true);
    }

    @Override
    public void onHud(GuiGraphics g) {
        List<Module> active = new ArrayList<>();
        for (Module m : WithFQClient.modules.all()) {
            if (m.isEnabled() && m != this) active.add(m);
        }
        active.sort(Comparator.comparingInt((Module m) -> mc.font.width(m.name)).reversed());

        int right = g.guiWidth() - 4;
        int y = 4;
        for (Module m : active) {
            int w = mc.font.width(m.name) + 8;
            g.fill(right - w, y, right, y + 12, HudModule.BG);
            g.fill(right - 2, y, right, y + 12, HudModule.ACCENT);
            g.drawString(mc.font, m.name, right - w + 3, y + 2, HudModule.WHITE, true);
            y += 12;
        }
    }
}
