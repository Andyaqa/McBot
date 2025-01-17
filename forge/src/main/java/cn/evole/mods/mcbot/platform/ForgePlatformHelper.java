package cn.evole.mods.mcbot.platform;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.Optional;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public Path getGamePath() {
        return FMLPaths.GAMEDIR.get();
    }

    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public Optional<Path> getResourcePath(String name) {
        return Optional.ofNullable(FMLLoader.getLoadingModList().getModFileById("mcbot").getFile().findResource(name));
    }

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }
}