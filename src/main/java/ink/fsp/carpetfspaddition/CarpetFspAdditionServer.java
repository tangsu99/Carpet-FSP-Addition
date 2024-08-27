package ink.fsp.carpetfspaddition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import carpet.utils.Translations;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CarpetFspAdditionServer implements CarpetExtension {
	public static final Set<String> customStopSpawnMobList = new HashSet<>();
	public static void init() {
		// init
		CarpetServer.manageExtension(new CarpetFspAdditionServer());
	}

	@Override
	public String version()
	{
		return CarpetFspAddition.MOD_ID;
	}

	@Override
	public void onGameStarted() {
		CarpetServer.settingsManager.parseSettingsClass(FspSettings.class);
		SettingsManager.registerGlobalRuleObserver((serverCommandSource, changedRule, userInput) -> {
			if (changedRule.name().equals("customStopSpawnMob")) {
				customStopSpawnMobList.addAll(Arrays.asList(FspSettings.customStopSpawnMob.split(",")));
			}
		});
    }

	@Override
	public Map<String, String> canHasTranslations(String lang) {
		// form plusls pca
		String dataJSON;
		try {
			dataJSON = IOUtils.toString(
					Objects.requireNonNull(Translations.class.getClassLoader().getResourceAsStream(
							String.format("assets/%s/lang/%s.json", CarpetFspAddition.MOD_ID, lang))),
					StandardCharsets.UTF_8);
		} catch (NullPointerException | IOException e) {
			return null;
		}
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		return gson.fromJson(dataJSON, new TypeToken<Map<String, String>>() {
		}.getType());
	}
}