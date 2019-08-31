package fun.pwsg.crates;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
    private File cFile;
    private Plugin plugin;
    private FileConfiguration fileConfiguration;

    public Config(String PluginName, String ConfigName) {
        this.plugin = Bukkit.getPluginManager().getPlugin(PluginName);

        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }

        this.cFile = new File(this.plugin.getDataFolder(), ConfigName);
        if (!this.cFile.exists()) {
            plugin.saveDefaultConfig();
            this.cFile = new File(this.plugin.getDataFolder(), ConfigName);
        }
        this.fileConfiguration = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(this.cFile);
    }

    public void saveDefaultConfig(String fileName) {
        if (!new File(this.plugin.getDataFolder(), fileName).exists()) {
            InputStream in = this.plugin.getResource(fileName);
            if (in == null) {
                this.plugin.getLogger().info("插件配置文件" + fileName + "不存在,无法读取");
                return;
            }
            File outFile = new File(this.plugin.getDataFolder(), fileName);
            int lastIndex = fileName.lastIndexOf('/');
            File outDir = new File(this.plugin.getDataFolder(), fileName.substring(0, lastIndex >= 0 ? lastIndex : 0));
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
            try {
                OutputStream out = new java.io.FileOutputStream(outFile);
                byte[] buf = new byte['Ѐ'];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.close();
                }
                out.close();
                in.close();
            }catch (IOException localIOException) {
                //
            }

        }
    }

    public FileConfiguration getConfig() {
        if (this.fileConfiguration != null) {
            return this.fileConfiguration;
        }
        return null;
    }

    public void saveConfig() {
        try {
            this.fileConfiguration.save(this.cFile);
        } catch (Exception e) {
            Bukkit.getLogger().warning("无法保存配置文件！");
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        this.fileConfiguration = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(this.cFile);
    }
}
