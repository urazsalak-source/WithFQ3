package net.withfq.client.core;

public enum Category {
    HUD("HUD"),
    RENDER("Render"),
    PLAYER("Player");

    public final String label;

    Category(String label) {
        this.label = label;
    }
}
