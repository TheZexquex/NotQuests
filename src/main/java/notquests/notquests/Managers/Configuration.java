package notquests.notquests.Managers;

import org.bukkit.Particle;

/**
 * This is the Configuration Class which contains the settings which can be configured in the General.conf
 *
 * @author Alessio Gravili
 */
public class Configuration {

    /**
     * MYSQL Database Connection Information
     */
    private String host, database, username, password;
    /**
     * MYSQL Database Connection Information
     */
    private int port;

    private boolean questPreviewUseGUI = true;
    private boolean userCommandsUseGUI = true;
    private boolean mySQLEnabled = false;

    public String placeholder_player_active_quests_list_horizontal_separator = " | ";

    public int placeholder_player_active_quests_list_horizontal_limit = -1;
    public int placeholder_player_active_quests_list_vertical_limit = -1;

    public boolean placeholder_player_active_quests_list_horizontal_use_displayname_if_available = true;
    public boolean placeholder_player_active_quests_list_vertical_use_displayname_if_available = true;

    private int maxActiveQuestsPerPlayer = -1;


    private boolean armorStandPreventEditing = true;


    //Particles
    private int citizensNPCQuestGiverIndicatorParticleSpawnInterval = 10;
    private int citizensNPCQuestGiverIndicatorParticleCount = 1;
    private Particle citizensNPCQuestGiverIndicatorParticleType = Particle.VILLAGER_ANGRY;
    private boolean citizensNPCQuestGiverIndicatorParticleEnabled = true;
    private double citizensNPCQuestGiverIndicatorParticleDisableIfTPSBelow = -1;

    private int armorStandQuestGiverIndicatorParticleSpawnInterval = 10;
    private int armorStandQuestGiverIndicatorParticleCount = 1;
    private Particle armorStandQuestGiverIndicatorParticleType = Particle.VILLAGER_ANGRY;
    private boolean armorStandQuestGiverIndicatorParticleEnabled = true;
    private double armorStandQuestGiverIndicatorParticleDisableIfTPSBelow = -1;


    private String languageCode = "en";


    public Configuration() {

    }

    public final String getDatabaseHost() {
        return host;
    }

    public void setDatabaseHost(final String host) {
        this.host = host;
    }

    public final int getDatabasePort() {
        return port;
    }

    public void setDatabasePort(final int port) {
        this.port = port;
    }

    public final String getDatabaseName() {
        return database;
    }

    public void setDatabaseName(final String database) {
        this.database = database;
    }

    public final String getDatabaseUsername() {
        return username;
    }

    public void setDatabaseUsername(final String username) {
        this.username = username;
    }

    public final String getDatabasePassword() {
        return password;
    }

    public void setDatabasePassword(final String password) {
        this.password = password;
    }

    public final boolean isQuestPreviewUseGUI() {
        return questPreviewUseGUI;
    }

    public void setQuestPreviewUseGUI(final boolean questPreviewUseGUI) {
        this.questPreviewUseGUI = questPreviewUseGUI;
    }

    public final boolean isMySQLEnabled(){
        return mySQLEnabled;
    }

    public void setMySQLEnabled(final boolean mySQLEnabled){
        this.mySQLEnabled = mySQLEnabled;
    }


    public final boolean isUserCommandsUseGUI() {
        return userCommandsUseGUI;
    }

    public void setUserCommandsUseGUI(final boolean userCommandsUseGUI) {
        this.userCommandsUseGUI = userCommandsUseGUI;
    }

    public final int getMaxActiveQuestsPerPlayer() {
        return maxActiveQuestsPerPlayer;
    }

    public void setMaxActiveQuestsPerPlayer(int maxActiveQuestsPerPlayer) {
        this.maxActiveQuestsPerPlayer = maxActiveQuestsPerPlayer;
    }


    //Particles Citizens
    public final int getCitizensNPCQuestGiverIndicatorParticleSpawnInterval() {
        return citizensNPCQuestGiverIndicatorParticleSpawnInterval;
    }

    public void setCitizensNPCQuestGiverIndicatorParticleSpawnInterval(final int citizensNPCQuestGiverIndicatorParticleSpawnInterval) {
        this.citizensNPCQuestGiverIndicatorParticleSpawnInterval = citizensNPCQuestGiverIndicatorParticleSpawnInterval;
    }

    public final Particle getCitizensNPCQuestGiverIndicatorParticleType() {
        return citizensNPCQuestGiverIndicatorParticleType;
    }

    public void setCitizensNPCQuestGiverIndicatorParticleType(final Particle citizensNPCQuestGiverIndicatorParticleType) {
        this.citizensNPCQuestGiverIndicatorParticleType = citizensNPCQuestGiverIndicatorParticleType;
    }

    public final int getCitizensNPCQuestGiverIndicatorParticleCount() {
        return citizensNPCQuestGiverIndicatorParticleCount;
    }

    public void setCitizensNPCQuestGiverIndicatorParticleCount(final int citizensNPCQuestGiverIndicatorParticleCount) {
        this.citizensNPCQuestGiverIndicatorParticleCount = citizensNPCQuestGiverIndicatorParticleCount;
    }

    public final boolean isCitizensNPCQuestGiverIndicatorParticleEnabled() {
        return citizensNPCQuestGiverIndicatorParticleEnabled;
    }

    public final void setCitizensNPCQuestGiverIndicatorParticleEnabled(boolean citizensNPCQuestGiverIndicatorParticleEnabled) {
        this.citizensNPCQuestGiverIndicatorParticleEnabled = citizensNPCQuestGiverIndicatorParticleEnabled;
    }

    public final double getCitizensNPCQuestGiverIndicatorParticleDisableIfTPSBelow() {
        return citizensNPCQuestGiverIndicatorParticleDisableIfTPSBelow;
    }

    public final void setCitizensNPCQuestGiverIndicatorParticleDisableIfTPSBelow(double disableIfTPSBelow) {
        this.citizensNPCQuestGiverIndicatorParticleDisableIfTPSBelow = disableIfTPSBelow;
    }


    //Particles ArmorStands
    public final int getArmorStandQuestGiverIndicatorParticleSpawnInterval() {
        return armorStandQuestGiverIndicatorParticleSpawnInterval;
    }

    public void setArmorStandQuestGiverIndicatorParticleSpawnInterval(final int armorStandQuestGiverIndicatorParticleSpawnInterval) {
        this.armorStandQuestGiverIndicatorParticleSpawnInterval = armorStandQuestGiverIndicatorParticleSpawnInterval;
    }

    public final Particle getArmorStandQuestGiverIndicatorParticleType() {
        return armorStandQuestGiverIndicatorParticleType;
    }

    public void setArmorStandQuestGiverIndicatorParticleType(final Particle armorStandQuestGiverIndicatorParticleType) {
        this.armorStandQuestGiverIndicatorParticleType = armorStandQuestGiverIndicatorParticleType;
    }

    public final int getArmorStandQuestGiverIndicatorParticleCount() {
        return armorStandQuestGiverIndicatorParticleCount;
    }

    public void setArmorStandQuestGiverIndicatorParticleCount(final int armorStandQuestGiverIndicatorParticleCount) {
        this.armorStandQuestGiverIndicatorParticleCount = armorStandQuestGiverIndicatorParticleCount;
    }

    public final boolean isArmorStandQuestGiverIndicatorParticleEnabled() {
        return armorStandQuestGiverIndicatorParticleEnabled;
    }

    public final void setArmorStandQuestGiverIndicatorParticleEnabled(boolean armorStandQuestGiverIndicatorParticleEnabled) {
        this.armorStandQuestGiverIndicatorParticleEnabled = armorStandQuestGiverIndicatorParticleEnabled;
    }

    public final double getArmorStandQuestGiverIndicatorParticleDisableIfTPSBelow() {
        return armorStandQuestGiverIndicatorParticleDisableIfTPSBelow;
    }

    public final void setArmorStandQuestGiverIndicatorParticleDisableIfTPSBelow(double disableIfTPSBelow) {
        this.armorStandQuestGiverIndicatorParticleDisableIfTPSBelow = disableIfTPSBelow;
    }


    public final String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(final String languageCode) {
        this.languageCode = languageCode;
    }

    public final boolean isArmorStandPreventEditing() {
        return armorStandPreventEditing;
    }

    public void setArmorStandPreventEditing(final boolean armorStandPreventEditing) {
        this.armorStandPreventEditing = armorStandPreventEditing;
    }
}
