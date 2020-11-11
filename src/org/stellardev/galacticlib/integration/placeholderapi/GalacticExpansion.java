package org.stellardev.galacticlib.integration.placeholderapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public abstract class GalacticExpansion extends PlaceholderExpansion {

    private final Plugin plugin;

    public GalacticExpansion(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getAuthor() {
        return this.plugin.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return this.plugin.getDescription().getVersion();
    }
}
