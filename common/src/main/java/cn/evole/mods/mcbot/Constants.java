package cn.evole.mods.mcbot;

import cn.evole.mods.mcbot.platform.Services;
import cn.evole.mods.mcbot.util.FileUtils;
import cn.evole.onebot.client.OneBotClient;
import cn.evole.onebot.sdk.util.GsonUtils;
import com.google.gson.Gson;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Constants {

    public static final String MOD_ID = "mcbot";
    public static final String MOD_NAME = "McBot";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static final ExecutorService msgExecutor = Executors.newCachedThreadPool();
    public static final ExecutorService cqExecutor = Executors.newSingleThreadExecutor();  // 创建CQ码处理线程池;
    public static final ExecutorService cmdExecutor = Executors.newSingleThreadExecutor();
    public static final Gson GSON = GsonUtils.getNullGson();
    public static final Path CONFIG_FOLDER = FileUtils.checkFolder(Services.PLATFORM.getGamePath().resolve("mcbot"));;

    public static boolean isShutdown = false;
    public static boolean connected = false;

    public static OneBotClient onebot;
    public static MinecraftServer SERVER = null;
}