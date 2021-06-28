package com.simibubi.create;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public enum AllKeys {

	TOOL_MENU("toolmenu", GLFW.GLFW_KEY_LEFT_ALT),
	SCHEMATIC_MOVE_X_POS("schematicXPositive", GLFW.GLFW_KEY_KP_8),
	SCHEMATIC_MOVE_X_NEG("schematicXNegative", GLFW.GLFW_KEY_KP_2),
	SCHEMATIC_MOVE_Z_POS("schematicZPositive", GLFW.GLFW_KEY_KP_6),
	SCHEMATIC_MOVE_Z_NEG("schematicZNegative", GLFW.GLFW_KEY_KP_4),
	SCHEMATIC_MOVE_Y_POS("schematicYPositive", GLFW.GLFW_KEY_KP_ADD),
	SCHEMATIC_MOVE_Y_NEG("schematicYNegative", GLFW.GLFW_KEY_KP_SUBTRACT),
	SCHEMATIC_ROTATE_CW("schematicRotateCW", GLFW.GLFW_KEY_KP_9),
	SCHEMATIC_ROTATE_CCW("schematicRotateCCW", GLFW.GLFW_KEY_KP_7),
	ACTIVATE_TOOL("", GLFW.GLFW_KEY_LEFT_CONTROL),

	;

	private KeyBinding keybind;
	private String description;
	private int key;
	private boolean modifiable;

	private AllKeys(String description, int defaultKey) {
		this.description = Create.ID + ".keyinfo." + description;
		this.key = defaultKey;
		this.modifiable = !description.isEmpty();
	}

	public static void register() {
		for (AllKeys key : values()) {
			key.keybind = new KeyBinding(key.description, key.key, Create.NAME);
			if (!key.modifiable)
				continue;

			ClientRegistry.registerKeyBinding(key.keybind);
		}
	}

	public KeyBinding getKeybind() {
		return keybind;
	}

	public boolean isPressed() {
		if (!modifiable)
			return isKeyDown(key);
		return keybind.isKeyDown();
	}

	public String getBoundKey() {
		return keybind.getBoundKeyLocalizedText()
			.getString()
			.toUpperCase();
	}

	public int getBoundCode() {
		return keybind.getKey()
			.getKeyCode();
	}

	public static boolean isKeyDown(int key) {
		return GLFW.glfwGetKey(Minecraft.getInstance()
			.getWindow()
			.getHandle(), key) != 0;
	}

	public static boolean ctrlDown() {
		return Screen.hasControlDown();
	}

	public static boolean shiftDown() {
		return Screen.hasShiftDown();
	}

	public static boolean altDown() {
		return Screen.hasAltDown();
	}

}
