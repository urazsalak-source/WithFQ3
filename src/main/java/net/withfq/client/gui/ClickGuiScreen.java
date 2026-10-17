package net.withfq.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.withfq.client.WithFQClient;
import net.withfq.client.config.ConfigManager;
import net.withfq.client.core.Category;
import net.withfq.client.core.Module;
import net.withfq.client.setting.BoolSetting;
import net.withfq.client.setting.NumberSetting;
import net.withfq.client.setting.Setting;
import org.lwjgl.glfw.GLFW;

/**
 * Sol tik: modulu ac/kapat. Sag tik: ayarlari ac/kapat.
 * Sayi ayarlarinda cubugun uzerine tiklayarak deger secilir.
 */
public class ClickGuiScreen extends Screen {
    private static final int PANEL_W = 110;
    private static final int HEADER_H = 16;
    private static final int MODULE_H = 14;
    private static final int SETTING_H = 12;

    private static final int ACCENT = 0xFF8A5CFF;
    private static final int WHITE = 0xFFFFFFFF;

    public ClickGuiScreen() {
        super(Component.literal("WithFQ"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        g.fill(0, 0, this.width, this.height, 0x88000000);
        layout(g, mouseX, mouseY, -1);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (layout(null, event.x(), event.y(), event.button())) return true;
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        if (event.key() == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            onClose();
            return true;
        }
        return super.keyPressed(event);
    }

    @Override
    public void removed() {
        ConfigManager.save(WithFQClient.modules);
    }

    /**
     * Cizim ve tiklama icin ortak yerlesim. g != null ise cizer,
     * button >= 0 ise o tiklamayi isler ve islendiyse true doner.
     */
    private boolean layout(GuiGraphics g, double mx, double my, int button) {
        int x = 20;
        for (Category category : Category.values()) {
            int y = 20;
            if (g != null) {
                g.fill(x, y, x + PANEL_W, y + HEADER_H, ACCENT);
                g.drawString(this.font, category.label, x + 4, y + 4, WHITE, true);
            }
            y += HEADER_H;

            for (Module m : WithFQClient.modules.all()) {
                if (m.category != category) continue;

                boolean hover = inside(mx, my, x, y, PANEL_W, MODULE_H);
                if (g != null) {
                    int bg = m.isEnabled() ? 0xDD5B3FD0 : (hover ? 0xDD2A2A33 : 0xDD16161C);
                    g.fill(x, y, x + PANEL_W, y + MODULE_H, bg);
                    g.drawString(this.font, m.name, x + 4, y + 3, WHITE, true);
                }
                if (button >= 0 && hover) {
                    if (button == 0) m.toggle();
                    else if (button == 1) m.expanded = !m.expanded;
                    return true;
                }
                y += MODULE_H;

                if (!m.expanded) continue;
                for (Setting s : m.settings) {
                    boolean over = inside(mx, my, x, y, PANEL_W, SETTING_H);
                    if (s instanceof BoolSetting b) {
                        if (g != null) {
                            g.fill(x, y, x + PANEL_W, y + SETTING_H, 0xDD0C0C10);
                            g.drawString(this.font, s.name + ": " + (b.get() ? "ON" : "OFF"),
                                    x + 8, y + 2, b.get() ? ACCENT : 0xFFAAAAAA, true);
                        }
                        if (button == 0 && over) {
                            b.toggle();
                            return true;
                        }
                    } else if (s instanceof NumberSetting n) {
                        if (g != null) {
                            g.fill(x, y, x + PANEL_W, y + SETTING_H, 0xDD0C0C10);
                            double ratio = (n.get() - n.min) / (n.max - n.min);
                            g.fill(x, y, x + (int) (PANEL_W * ratio), y + SETTING_H, 0xDD3A2A80);
                            String value = n.step >= 1 ? String.valueOf(n.getInt()) : String.format("%.1f", n.get());
                            g.drawString(this.font, s.name + ": " + value, x + 8, y + 2, WHITE, true);
                        }
                        if (button == 0 && over) {
                            double ratio = (mx - x) / PANEL_W;
                            n.set(n.min + ratio * (n.max - n.min));
                            return true;
                        }
                    }
                    y += SETTING_H;
                }
            }
            x += PANEL_W + 10;
        }
        return false;
    }

    private static boolean inside(double mx, double my, int x, int y, int w, int h) {
        return mx >= x && mx < x + w && my >= y && my < y + h;
    }
}
