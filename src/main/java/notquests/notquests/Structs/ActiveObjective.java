package notquests.notquests.Structs;


import notquests.notquests.NotQuests;
import notquests.notquests.Structs.Objectives.EscortNPCObjective;
import notquests.notquests.Structs.Objectives.Objective;
import notquests.notquests.Structs.Objectives.OtherQuestObjective;
import notquests.notquests.Structs.Triggers.ActiveTrigger;
import notquests.notquests.Structs.Triggers.TriggerTypes.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This is a special object for active objectives. Apart from the main Objective object which stores information about what defines the objective itself,
 * it contains other information like the ActiveQuest for which the objective is (the ActiveQuest object ALSO stores this ActiveObjective object, so they
 * can access each other).
 * <p>
 * It also contains the progress and information about if this active objective has been completed - because there is NO CompletedObjective object. Completed
 * objectives will still be instances of the ActiveObjective class.
 *
 * @author Alessio Gravili
 */
public class ActiveObjective {
    private final NotQuests main;
    private final Objective objective;
    private final ActiveQuest activeQuest;
    private final int objectiveID;
    private long currentProgress;
    private boolean unlocked = false;
    private boolean hasBeenCompleted = false;

    public ActiveObjective(final NotQuests main, final int objectiveID, final Objective objective, final ActiveQuest activeQuest) {
        this.main = main;
        this.objectiveID = objectiveID;
        this.objective = objective;
        this.activeQuest = activeQuest;
        currentProgress = 0;

    }

    public final void setUnlocked(final boolean unlocked, final boolean notifyPlayer, final boolean triggerAcceptQuestTrigger) {
        if (this.unlocked != unlocked) {
            this.unlocked = unlocked;
            if (unlocked) {
                if (triggerAcceptQuestTrigger) {
                    for (final ActiveTrigger activeTrigger : getActiveQuest().getActiveTriggers()) {
                        if (activeTrigger.getTrigger().getTriggerType() == TriggerType.BEGIN) { //Start the quest
                            if (activeTrigger.getTrigger().getApplyOn() >= 1) { //Objective and not Quest
                                if (getObjectiveID() == activeTrigger.getTrigger().getApplyOn()) {
                                    if (activeTrigger.getTrigger().getWorldName().equalsIgnoreCase("ALL")) {
                                        activeTrigger.addAndCheckTrigger(activeQuest);
                                    } else {
                                        final Player player = Bukkit.getPlayer(getQuestPlayer().getUUID());
                                        if (player != null && player.getWorld().getName().equalsIgnoreCase(activeTrigger.getTrigger().getWorldName())) {
                                            activeTrigger.addAndCheckTrigger(activeQuest);
                                        }
                                    }


                                }
                            }
                        }
                    }
                }


                if (objective instanceof EscortNPCObjective escortNPCObjective) {
                    if (main.isCitizensEnabled()) {
                        activeQuest.getCitizensHandler().handleEscortNPCObjectiveForActiveObjective(escortNPCObjective, activeQuest);
                    }

                }


                //TODO: What?
                if (objective instanceof OtherQuestObjective otherQuestObjective) {
                    if (otherQuestObjective.isCountPreviousCompletions()) {
                        for (CompletedQuest completedQuest : getQuestPlayer().getCompletedQuests()) {
                            if (completedQuest.getQuest().equals(otherQuestObjective.getOtherQuest())) {
                                addProgress(1, -1);
                            }
                        }
                    }
                }
                if (notifyPlayer) {
                    final Player player = Bukkit.getPlayer(getQuestPlayer().getUUID());
                    if (player != null) {
                        main.getQuestManager().sendActiveObjective(player, this);
                    }
                }

            }
        }

    }

    public final boolean isUnlocked() {
        return unlocked;
    }

    public void updateUnlocked(final boolean notifyPlayer, final boolean triggerAcceptQuestTrigger) {

        boolean foundStillDependant = false;
        for (final Objective dependantObjective : objective.getDependantObjectives()) {
            for (final ActiveObjective activeObjective : activeQuest.getActiveObjectives()) {
                if (activeObjective.getObjectiveID() == dependantObjective.getObjectiveID()) {
                    foundStillDependant = true;
                    if (!isUnlocked()) {
                        setUnlocked(false, notifyPlayer, triggerAcceptQuestTrigger);
                    }

                    break;
                }
            }
            if (foundStillDependant) {
                break;
            }
        }
        if (!foundStillDependant) {
            setUnlocked(true, notifyPlayer, triggerAcceptQuestTrigger);

        }


    }

    public final ArrayList<ActiveObjective> getObjectivesWhichStillNeedToBeCompletedBeforeUnlock() {
        final ArrayList<ActiveObjective> stillDependantObjectives = new ArrayList<>();
        for (final Objective dependantObjective : objective.getDependantObjectives()) {
            for (final ActiveObjective activeObjective : activeQuest.getActiveObjectives()) {
                if (activeObjective.getObjectiveID() == dependantObjective.getObjectiveID()) {
                    stillDependantObjectives.add(activeObjective);
                    break;
                }
            }


        }

        return stillDependantObjectives;
    }

    public final Objective getObjective() {
        return objective;
    }

    public final long getProgressNeeded() {
        return objective.getProgressNeeded();
    }

    public final long getCurrentProgress() {
        return currentProgress;
    }

    //For Citizens NPCs
    public void addProgress(long i, final int NPCID) {
        currentProgress += i;
        if (isCompleted(NPCID)) {
            setHasBeenCompleted(true);
            activeQuest.notifyActiveObjectiveCompleted(this, false, NPCID);
        }
    }

    //For Armor Stands
    public void addProgress(long i, final UUID armorStandUUID) {
        currentProgress += i;
        if (isCompleted(armorStandUUID)) {
            setHasBeenCompleted(true);
            activeQuest.notifyActiveObjectiveCompleted(this, false, armorStandUUID);
        }
    }

    public void addProgressSilent(long i, final int NPCID) {
        currentProgress += i;
        if (isCompleted(NPCID)) {
            setHasBeenCompleted(true);
            activeQuest.notifyActiveObjectiveCompleted(this, true, NPCID);
        }
    }

    public void removeProgress(int i, boolean capAtZero) {

        if (capAtZero) {
            if (currentProgress - i < 0) {
                if (currentProgress > 0) {
                    currentProgress = 0;
                }
            } else {
                currentProgress -= i;
            }
        } else {
            currentProgress -= i;
        }


    }

    //For Citizens NPCs
    public final boolean isCompleted(final int NPCID) {
        if (getObjective().getCompletionNPCID() == -1 || getObjective().getCompletionNPCID() == NPCID) {
            return currentProgress >= objective.getProgressNeeded();
        } else {
            return false;
        }

    }

    //For Armor Stands
    public final boolean isCompleted(final UUID armorStandUUID) {
        if (getObjective().getCompletionArmorStandUUID() == null || getObjective().getCompletionArmorStandUUID().equals(armorStandUUID)) {
            return currentProgress >= objective.getProgressNeeded();
        } else {
            return false;
        }

    }

    public final QuestPlayer getQuestPlayer() {
        return activeQuest.getQuestPlayer();
    }

    public final ActiveQuest getActiveQuest() {
        return activeQuest;
    }

    public final int getObjectiveID() {
        return objectiveID;
    }

    public final boolean hasBeenCompleted() {
        return hasBeenCompleted;
    }

    public void setHasBeenCompleted(final boolean hasBeenCompleted) {
        // System.out.println("§4§lSet has been completed to: §b" + hasBeenCompleted + " §cfor objective with ID §b" + getObjectiveID());
        this.hasBeenCompleted = hasBeenCompleted;
    }
}
