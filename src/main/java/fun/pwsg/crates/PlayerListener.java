package fun.pwsg.crates;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;
import java.util.Random;

public class PlayerListener implements Listener{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        if (new Config("GoldAss", "coins.yml").getConfig().getString(event.getPlayer().getName()) == null) {
            Config config = new Config("GoldAss", "coins.yml");
            config.getConfig().set(event.getPlayer().getName(),Integer.valueOf(0));
            config.saveConfig();
        }
        //        try {
        //            stat1 = sql.createStatement();
        //            stat = sql.prepareStatement("alter table boy add "+event.getPlayer().getName()+" int;");
        //            stat.executeUpdate();
        //            stat.close();
        //            ResultSet rs = stat1.executeQuery("select " +event.getPlayer().getName()+ " from boy LIMIT 1 offset (SELECT COUNT(*) - 1 FROM boy);");
        //            while (rs.next()) {
        //                return;
        //            }
        //            stat1 = sql.createStatement();
        //            stat = sql.prepareStatement("insert into boy(" +event.getPlayer().getName()+ ")" + " values(0);");
        //            stat.executeUpdate();
        //            stat.close();
        //        } catch (SQLException e){
        //            getLogger().info("插件正在运行！");
        //        }

    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        if((event.getClickedBlock() !=null) && (Material.CAULDRON.equals(event.getClickedBlock().getType()))) {
            if ((new Config("GoldAss", "coins.yml").getConfig().getInt(event.getPlayer().getName()) < 10) && (new Config("GoldAss", "coins.yml").getConfig().getInt(event.getPlayer().getName()) != 0 )) {
                event.getPlayer().sendMessage("§f§lPW§6§lSG §8§l> §c你没有足够的男♂魂,至少需要10个");
                return;
            }
            if (new Config("GoldAss", "coins.yml").getConfig().getInt(event.getPlayer().getName()) <= 0) {
                event.getPlayer().sendMessage("§f§lPW§6§lSG §8§l> §c你是个弱子，快去打几局扭转♂乾坤");
                return;
            }
            Config config = new Config("GoldAss", "coins.yml");
            config.getConfig().set(event.getPlayer().getName(),
                    Integer.valueOf(config.getConfig().getInt(event.getPlayer().getName()) - 10));
            config.saveConfig();
            int b = new Random().nextInt(2500) + 1000;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money give " + event.getPlayer().getName() + " " + b);
            event.getPlayer().sendMessage("§f§lPW§6§lSG §8§l> §c 你在聚宝盆里获得了" + b + "枚硬币");
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&6聚宝盆"))) {
            event.setCancelled(true);
        }
    }

/*    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        final LivingEntity entity = event.getEntity();
        Config config = new Config("GoldAss", "coins.yml");
        if (entity.getKiller().getPlayer().getName() == null) {
            return;
        }
        if (entity.getKiller().getPlayer().getName() == event.getEntity().getPlayer().getName()) {
            config.getConfig().set(entity.getKiller().getPlayer().getName(), Integer.valueOf(config.getConfig().getInt(entity.getKiller().getPlayer().getName()) - 1));
            entity.getKiller().getPlayer().sendMessage("§f§lPW§6§lSG §8§l> §c 你失去了一点男♂魂，你现在有" + config.getConfig().getInt(entity.getKiller().getPlayer().getName()) + "点男♂魂，你离弱子又进了一步！");
            config.saveConfig();
        }else {
            config.getConfig().set(entity.getKiller().getPlayer().getName(), Integer.valueOf(config.getConfig().getInt(entity.getKiller().getPlayer().getName()) + 1));
            config.saveConfig();
            entity.getKiller().getPlayer().sendMessage("§f§lPW§6§lSG §8§l> §c 你获得了一点男♂魂，你现在有" + config.getConfig().getInt(entity.getKiller().getPlayer().getName()) + "点男♂魂");
        }
    }
 */
    @EventHandler
    public void onKilled(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            Player damaged = (Player) event.getEntity();
            if(event.getEntity() instanceof Player) {
                Player damager = (Player) event.getDamager();
                Config config = new Config("GoldAss", "coins.yml");
                if((damaged.getHealth()-event.getDamage()) <= 1 && damager == damaged){
                    config.getConfig().set(event.getDamager().getName(), Integer.valueOf(config.getConfig().getInt(event.getDamager().getName()) - 1));
                    event.getDamager().sendMessage("§f§lPW§6§lSG §8§l> §c 你失去了一点男♂魂，你现在有" + config.getConfig().getInt(event.getDamager().getName()) + "点男♂魂，你离弱子又进了一步！");
                    config.saveConfig();
                }
                if((damaged.getHealth()-event.getDamage()) <= 1) {
                    config.getConfig().set(event.getDamager().getName(), Integer.valueOf(config.getConfig().getInt(event.getDamager().getName()) + 1));
                    config.saveConfig();
                    event.getDamager().sendMessage("§f§lPW§6§lSG §8§l> §c 你获得了一点男♂魂，你现在有" + config.getConfig().getInt(event.getDamager().getName()) + "点男♂魂");
                }
            }
        }
    }
}