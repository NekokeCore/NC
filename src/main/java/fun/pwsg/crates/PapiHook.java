package fun.pwsg.crates;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PapiHook extends PlaceholderExpansion {

    @Override
    public String getRequiredPlugin(){
        return "GoldAss";
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "0";
        }
        // %goldass_boy%
        if(identifier.equals("boy")){
            return new Config("GoldAss", "coins.yml").getConfig().getString(player.getName());
        }
        return "0";
    }

    @Override
    public String getIdentifier() {
        return "GoldAss";
    }

    @Override
    public String getAuthor() {
        return "核心";
    }

    @Override
    public String getVersion() {
        return "2.0.0";
    }
}
