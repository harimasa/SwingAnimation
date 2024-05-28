package ru.femboypig;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.femboypig.modmenu.SAC;
import ru.femboypig.modmenu.SAS;

public class SwingAnim implements ModInitializer {
	public static final MinecraftClient mc = MinecraftClient.getInstance();
	public static KeyBinding configAnim;

    public static final Logger LOGGER = LoggerFactory.getLogger("swinganim");

	@Override
	public void onInitialize() {
		SAC.load();
		configAnim = KeyBindingHelper.registerKeyBinding(new KeyBinding("anim.key", GLFW.GLFW_KEY_UNKNOWN, "ViewModel"));
		ClientTickEvents.END_CLIENT_TICK.register(mc -> {
			if (configAnim.wasPressed()) {
				mc.setScreen(new SAS(null));
			}
		});
		LOGGER.info("SwingAnimations Mod is loaded.");
	}
}