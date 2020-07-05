package me.spypat.servercore.perm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class PermPlayer{


	static Map<Player, List<String>>perms = new HashMap<Player, List<String>>();
	public static void givePerm(Player p, String perm){
		p.sendMessage(Text.of("6"));
		if(!perms.containsKey(p)){
			ArrayList<String> temp = new ArrayList<String>();
			perms.put(p, temp);
			p.sendMessage(Text.of("7"));
		}
		perms.get(p).add(perm);
		p.sendMessage(Text.of("8"));
	}
	public static void takePerm(Player p, String perm){
		if(!perms.containsKey(p))
			return;
		if(!perms.get(p).contains(perm))
			return;
		perms.get(p).remove(perm);
	}
	public static boolean hasPerm(Player p, String perm){
		p.sendMessage(Text.of("1"));
		if(!perms.containsKey(p)){
			p.sendMessage(Text.of("2"));
			return false;
		}
		if(!perms.get(p).contains(perm)){
			p.sendMessage(Text.of("3"));
			return false;
		}
		p.sendMessage(Text.of("4"));
		return true;
	}
}
