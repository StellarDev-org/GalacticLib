package org.stellardev.galacticlib.task;

import com.massivecraft.massivecore.ModuloRepeatTask;
import com.massivecraft.massivecore.util.TimeUnit;
import org.stellardev.galacticlib.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TaskTeleportTimer extends ModuloRepeatTask {

    private static final TaskTeleportTimer i = new TaskTeleportTimer();
    public static TaskTeleportTimer get() { return i; }

    private final Map<UUID, TeleportCompletionObject> teleportUuidMap = new HashMap<>();

    @Override
    public long getDelayMillis() {
        return TimeUnit.MILLIS_PER_SECOND;
    }

    @Override
    public void invoke(long l) {
        new HashMap<>(this.teleportUuidMap).entrySet().stream()
                .filter(entry -> System.currentTimeMillis() >= entry.getValue().getTimeMs())
                .forEach(entry -> {
                    entry.getValue().getCallback().call(true);
                    this.teleportUuidMap.remove(entry.getKey());
                });
    }

    public void addUserToMap(UUID uuid, long completionMs, Callback<Boolean> callback) {
        this.teleportUuidMap.put(uuid, new TeleportCompletionObject(completionMs, callback));
    }

    public void cancelTeleport(UUID uuid) {
        TeleportCompletionObject teleportCompletionObject = this.teleportUuidMap.remove(uuid);

        if(teleportCompletionObject == null) return;

        teleportCompletionObject.getCallback().call(false);
    }

    public static class TeleportCompletionObject {

        private final Callback<Boolean> callback;
        private final long timeMs;

        public TeleportCompletionObject(long timeMs, Callback<Boolean> callback) {
            this.timeMs = timeMs;
            this.callback = callback;
        }

        public long getTimeMs() {
            return this.timeMs;
        }

        public Callback<Boolean> getCallback() {
            return this.callback;
        }
    }
}
