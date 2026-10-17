package net.withfq.client.modules;

import net.withfq.client.core.Category;
import net.withfq.client.core.Module;

public class ToggleSprintModule extends Module {
    public ToggleSprintModule() {
        super("ToggleSprint", "Ileri giderken otomatik kosar.", Category.PLAYER);
    }

    @Override
    public void onTick() {
        if (mc.options.keyUp.isDown() && !mc.player.horizontalCollision) {
            mc.player.setSprinting(true);
        }
    }
}
