package fun.pwsg.crates;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

//import java.sql.*;

public class Main extends JavaPlugin {
    //static Connection sql;
    //static PreparedStatement stat;
    //static Statement stat1;
    @Override
    public void onEnable(){
        //与PlaceholderAPI挂钩
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") !=null){
            new PapiHook().register();
            //注册监听器
            getServer().getPluginManager().registerEvents(new PlayerListener(), this);
            getLogger().info("与PlaceholderAPI成功挂钩！");
        } else {
            throw new RuntimeException("未找到PlaceholderAPI，插件无法继续运行！");
        }
        //生成SQlite
        //        getLogger().info("正在检查插件是否释放配置文件夹！");
        //        if(getDataFolder().exists()) {
        //            getLogger().info("检查成功，插件将会继续运行");
        //        }else{
        //            getLogger().info("检查失败，插件将会自动创建文件夹！");
        //            getDataFolder().mkdir();
        //        }，并于SQlite连接

        //与SQlite      getLogger().info("下面尝试与SQLite进行连接！");
        //        File dataFolder = new File(getDataFolder(), "coin.db");
        //        try {
        //            Class.forName("org.sqlite.JDBC");
        //            sql = DriverManager.getConnection("jdbc:sqlite:"+ dataFolder);
        //            stat = Main.sql.prepareStatement("CREATE TABLE IF NOT EXISTS boy(Player text,Coin text);");
        //            stat.executeUpdate();
        //            stat.close();
        //        } catch (ClassNotFoundException | SQLException e) {
        //            e.printStackTrace();
        //        }
        //        getLogger().info("SQLite连接成功！");尝试连接

        //返回成功信息
        getLogger().info("插件已启动！");
    }
    @Override
    public void onDisable() {
        //    getLogger().info("正在与SQLite数据库断开连接！");
        //        try {
        //            if (sql!=null && !sql.isClosed()){
        //                sql.close();
        //            }
        //        } catch(Exception e) {
        //            e.printStackTrace();
        //        }
        //        getLogger().info("与SQLite数据库断开连接成功！");
        getLogger().info("插件已关闭！");
    }

}
