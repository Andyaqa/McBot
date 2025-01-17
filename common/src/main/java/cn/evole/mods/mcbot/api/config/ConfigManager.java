package cn.evole.mods.mcbot.api.config;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.platform.Services;
import cn.evole.mods.mcbot.util.FileUtils;
import lombok.Getter;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.configurate.ConfigurateException;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cn.evole.mods.mcbot.Constants.CONFIG_FOLDER;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/11 19:16
 * @Description:
 */
public class ConfigManager implements AutoCloseable {

    @Getter
    private static ConfigManager instance;
    private final Path dir;
    private final Map<Class<?>, ConfigHandler<?>> configs = new ConcurrentHashMap<>();

    public ConfigManager(Path dir) {
        this.dir = dir;
        instance = this;
    }

    @Override
    public void close() {
        for (ConfigHandler<?> configHandler : configs.values()) {
            try {
                configHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveAllConfig() {
        configs.forEach((aClass, config) -> {
            try {
                config.saveToFile();
            } catch (ConfigurateException e) {
                e.printStackTrace();
            }
        });

    }

    public void saveConfig(final Class<?> config) {
        try {
            configs.get(config).saveToFile();
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
    }

    public void initConfigs(final Class<?>... configs) {
        for (final Class<?> config : configs) {
            initConfig(dir, config);
        }
    }

    private void initConfig(final Path dir, final Class<?> config) {
        if (!dir.toFile().exists()) {
            dir.toFile().mkdirs();
        }
        Constants.LOGGER.info("Initialising Configuration: {}/{}", dir.getFileName().toString(), config.getSimpleName());
        String fileName = config.getSimpleName().toLowerCase() + ".yml";
        configs.put(config, new ConfigHandler<>(dir, fileName, config));
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfig(final Class<T> config) {
        return (T) configs.get(config).getConfig();
    }

}
