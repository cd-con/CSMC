package dev.danablend.counterstrike;

import dev.danablend.counterstrike.csplayer.TeamEnum;
import dev.danablend.counterstrike.enums.Weapon;
import dev.danablend.counterstrike.enums.WeaponQA;
import dev.danablend.counterstrike.enums.WeaponType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private static FileConfiguration config = CounterStrike.i.getConfig();

    public static String counterTerroristShopName = "Buy Menu - Counter Terrorist";
    public static String terroristShopName = "Buy Menu - Terrorist";

    public static boolean GAME_ENABLED = config.getBoolean("enabled", true);
    public static boolean DEBUGGING_ENABLED = config.getBoolean("debug", false);

    public static int MIN_PLAYERS = config.getInt("min-players", 5);
    public static int MAX_PLAYERS = config.getInt("max-players", 10);
    public static int ROUNDS_TO_WIN = config.getInt("rounds-to-win", 16);
    public static int MAX_ROUNDS = config.getInt("max-rounds", 30);

    public static int STARTING_MONEY = config.getInt("starting-money", 800);
    public static int MONEY_ON_VICTORY = config.getInt("money-on-win-reward", 3000);
    public static int MONEY_ON_LOSS = config.getInt("money-on-loss-reward", 2000);
    public static int BOMB_TIMER = config.getInt("bomb-timer", 45);
    public static int SHOP_PHASE_DURATION = config.getInt("shop-phase-duration", 15);
    public static int MATCH_DURATION = config.getInt("match-duration", 120);

    public static int KNIFE_SPEED = config.getInt("knife-speed", 2);

    public static Material BOMB_MATERIAL = Material.getMaterial(config.getString("bomb-block", "BEDROCK"));
    public static float BOMB_DEFUSE_TIME = (float) config.getDouble("bomb-defuse-time", 5f);

    public static double SPAWN_RADIUS_X = 7.0;
    public static double SPAWN_RADIUS_Z = 7.0;

    public static boolean FRIENDLY_FIRE_ENABLED = config.getBoolean("friendly-fire-enabled", false);
    
    public void reloadConfig(){
        // Reloads only config file, weapons won't be reloaded!
        
        // TODO Implement renaming
        counterTerroristShopName = "Buy Menu - Counter Terrorist";
        terroristShopName = "Buy Menu - Terrorist";

        GAME_ENABLED = config.getBoolean("enabled", true);
        DEBUGGING_ENABLED = config.getBoolean("debug", false);

        MIN_PLAYERS = config.getInt("min-players", 5);
        MAX_PLAYERS = config.getInt("max-players", 10);
        ROUNDS_TO_WIN = config.getInt("rounds-to-win", 16);
        MAX_ROUNDS = config.getInt("max-rounds", 30);

        STARTING_MONEY = config.getInt("starting-money", 800);
        MONEY_ON_VICTORY = config.getInt("money-on-win-reward", 3000);
        MONEY_ON_LOSS = config.getInt("money-on-loss-reward", 2000);
        BOMB_TIMER = config.getInt("bomb-timer", 45);
        SHOP_PHASE_DURATION = config.getInt("shop-phase-duration", 15);
        MATCH_DURATION = config.getInt("match-duration", 120);

        KNIFE_SPEED = config.getInt("knife-speed", 2);

        Material BOMB_MATERIAL = Material.getMaterial(config.getString("bomb-block", "BEDROCK"));
        BOMB_DEFUSE_TIME = (float) config.getDouble("bomb-defuse-time", 5f);
        
        // TODO Implement spawn radius
        SPAWN_RADIUS_X = 7.0;
        SPAWN_RADIUS_Z = 7.0;

        FRIENDLY_FIRE_ENABLED = config.getBoolean("friendly-fire-enabled", false);
    }


    public void loadWeapons() {
        ConfigurationSection weapons = config.getConfigurationSection("weapons");

        for (String weaponId : weapons.getValues(false).keySet()) {
            ConfigurationSection weapon = weapons.getConfigurationSection(weaponId);
            WeaponType weaponType = WeaponType.valueOf(weapon.getString("weapon-type"));
            String name = weapon.getString("name");
            String displayName = weapon.isSet("display-name") ? weapon.getString("display-name") : name;
            displayName = ChatColor.translateAlternateColorCodes('&', displayName);
            TeamEnum team = TeamEnum.valueOf(weapon.getString("team"));
            int cost = weapon.getInt("cost");
            Material material = Material.valueOf(weapon.getString("material"));
            Weapon weaponObj;

            int damage = weapon.getInt("damage");
            int magazines = weapon.getInt("magazines");
            int magazineCapacity = weapon.getInt("magazine-capacity");
            double reloadSpeed = weapon.getDouble("reload-speed");

            weaponObj = new WeaponQA(weaponId, name, displayName, material, cost, magazineCapacity, magazines, damage, reloadSpeed, team, weaponType);
            Weapon.addWeapon(weaponObj);
        }
    }
}
