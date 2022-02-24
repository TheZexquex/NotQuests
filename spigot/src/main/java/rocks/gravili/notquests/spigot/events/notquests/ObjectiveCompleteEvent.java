/*
 * NotQuests - A Questing plugin for Minecraft Servers
 * Copyright (C) 2021-2022 Alessio Gravili
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package rocks.gravili.notquests.spigot.events.notquests;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import rocks.gravili.notquests.spigot.structs.ActiveObjective;
import rocks.gravili.notquests.spigot.structs.ActiveQuest;
import rocks.gravili.notquests.spigot.structs.QuestPlayer;

public class ObjectiveCompleteEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final QuestPlayer questPlayer;
    private final ActiveObjective activeObjective;
    private final ActiveQuest activeQuest;
    private boolean isCancelled;

    public ObjectiveCompleteEvent(final QuestPlayer questPlayer, final ActiveObjective activeObjective, final ActiveQuest activeQuest) {
        super(true);

        this.questPlayer = questPlayer;
        this.activeObjective = activeObjective;
        this.activeQuest = activeQuest;

        this.isCancelled = false;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public QuestPlayer getQuestPlayer() {
        return this.questPlayer;
    }

    public ActiveObjective getActiveObjective() {
        return this.activeObjective;
    }

    public ActiveQuest getActiveQuest() {
        return this.activeQuest;
    }
}