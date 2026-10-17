package net.withfq.client.modules;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.withfq.client.core.Category;
import net.withfq.client.core.Module;

/** Istemci tarafinda gece gorusu efekti uygulayarak her yeri aydinlatir. */
public class FullbrightModule extends Module {
    public FullbrightModule() {
        super("Fullbright", "Her yer aydinlik gorunur.", Category.RENDER);
    }

    @Override
    public void onTick() {
        mc.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 0, false, false));
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) mc.player.removeEffect(MobEffects.NIGHT_VISION);
    }
}
