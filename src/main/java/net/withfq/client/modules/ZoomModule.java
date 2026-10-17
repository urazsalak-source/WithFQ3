package net.withfq.client.modules;

import net.withfq.client.core.Category;
import net.withfq.client.core.Module;
import net.withfq.client.setting.NumberSetting;

/** FOV degerini dusurerek yakinlastirir; kapatinca eski FOV geri gelir. */
public class ZoomModule extends Module {
    private final NumberSetting zoomFov = add(new NumberSetting("Zoom FOV", 30, 30, 90, 1));
    private int previousFov = 70;

    public ZoomModule() {
        super("Zoom", "Yakinlastirma (tusa bas ac/kapat).", Category.RENDER);
    }

    @Override
    public boolean persistent() {
        return false;
    }

    @Override
    protected void onEnable() {
        previousFov = mc.options.fov().get();
        mc.options.fov().set(zoomFov.getInt());
    }

    @Override
    public void onTick() {
        if (mc.options.fov().get() != zoomFov.getInt()) mc.options.fov().set(zoomFov.getInt());
    }

    @Override
    protected void onDisable() {
        mc.options.fov().set(previousFov);
    }
}
