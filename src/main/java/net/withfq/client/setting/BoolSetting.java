package net.withfq.client.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class BoolSetting extends Setting {
    private boolean value;

    public BoolSetting(String name, boolean def) {
        super(name);
        this.value = def;
    }

    public boolean get() {
        return value;
    }

    public void set(boolean v) {
        this.value = v;
    }

    public void toggle() {
        this.value = !this.value;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(value);
    }

    @Override
    public void fromJson(JsonElement e) {
        if (e != null && e.isJsonPrimitive()) value = e.getAsBoolean();
    }
}
