package ee.taltech.deepdarkdungeon.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;

import java.io.IOException;

public class DesktopLauncher {
	public DesktopLauncher() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
		config.fullscreen = false;
		config.resizable = false;
		System.out.println(config.width);
		System.out.println(config.height);
		new LwjglApplication(new DeepDarkDungeonGame(config.width, config.height), config);
	}
}
