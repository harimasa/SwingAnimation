package ru.femboypig.modmenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import ru.femboypig.SwingAnim;

import java.lang.reflect.Modifier;
import java.nio.file.Files;

public class SAC {
    public static final transient Gson GSON = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();

    // Enabled Animations?
    public static boolean enabledAnim = false;
    public static boolean enabledMainHand = false;
    public static boolean enabledOffHand = false;
    public static boolean slowAnim = false;

    // Value Slow Animation
    public static float slowAnimValue = 0.5f;

    // offhand
    public static boolean affectoffhand = false;
    public static float positionX = 0.5f;
    public static float positionY = 0.5f;
    public static float positionZ = 0.5f;
    public static float scaleoff = 0.5f;
    public static float scale = 0.5f;

    // logs
    public static transient float slowAnimSpeed;
    public static transient float getPositionX;
    public static transient float getPositionY;
    public static transient float getPositionZ;
    public static transient float getScaleoff;
    public static transient float getScale;

    // Load Config
    public static void load() {
        try {
            var path = FabricLoader.getInstance().getConfigDir().resolve("swinganim.json");
            if (Files.exists(path)) {
                GSON.fromJson(Files.readString(path), SAC.class);
            }
        } catch (Throwable t) {
            SwingAnim.LOGGER.warn("Failed to load mod config", t);
        }
        calculateSpeedAnim();
        calculatePosX();
        calculatePosY();
        calculatePosZ();
        caclculateScale();
        caclculateScaleoff();
    }

    // Save Config
    public static void save() {
        try {
            var path = FabricLoader.getInstance().getConfigDir().resolve("swinganim.json");
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(new SAC()));
        } catch (Throwable t) {
            SwingAnim.LOGGER.warn("Failed to save mod config", t);
        }
    }
    public static void calculateSpeedAnim() {
        slowAnimSpeed = 1 + slowAnimValue * (50 - 1);
    }
    public static void calculatePosX() {
        getPositionX = -3 + positionX * (3 - (-3));
    }
    public static void calculatePosY() {
        getPositionY = -3 + positionY * (3 - (-3));
    }
    public static void calculatePosZ() {
        getPositionZ = -3 + positionZ * (3 - (-3));
    }
    public static void caclculateScaleoff() {
        getScaleoff = 0.1f + scaleoff * (3 - 0.1f);
    }
    public static void caclculateScale() {
        getScale = 0.1f + scale * (1 - 0.1f);
    }
}