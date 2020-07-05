package me.spypat.servercore.currency;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.api.entity.living.player.Player;

public class Currency {
	static Map<String, Integer> tokens = new HashMap<String, Integer>();
	static Map<String, Integer> coins = new HashMap<String, Integer>();
	
	public static void giveTokens(Player p, Integer i){
		if(!tokens.containsKey(p.getName())){
			tokens.put(p.getName(), i);
			return;
		}
		tokens.replace(p.getName(), tokens.get(p.getName())+i);
	}
	public static void giveCoins(Player p, Integer i){
		if(!coins.containsKey(p.getName())){
			coins.put(p.getName(), i);
			return;
		}
		coins.replace(p.getName(), coins.get(p.getName())+i);
	}
	public static boolean takeTokens(Player p, Integer i){
		if(!tokens.containsKey(p.getName()))
			return false;
		if(i>tokens.get(p.getName()))
			return false;
		tokens.replace(p.getName(), tokens.get(p.getName())-i);
		return true;
	}
	public static boolean takeCoins(Player p, Integer i){
		if(!coins.containsKey(p.getName()))
			return false;
		if(i>coins.get(p.getName()))
			return false;
		coins.replace(p.getName(), coins.get(p.getName())-i);
		return true;
	}
	public static int getTokens(Player p){
		if(!tokens.containsKey(p.getName()))
			return 0;
		return tokens.get(p.getName());
	}
	public static int getCoins(Player p){
		if(!coins.containsKey(p.getName()))
			return 0;
		return coins.get(p.getName());
	}
}
