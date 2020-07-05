package me.spypat.servercore.rank;

import java.util.List;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import me.spypat.servercore.perm.PermPlayer;

public class Rank {

	String name;
	Text tag;
	List<String> perms;
	List<Player> players;
	public Rank(String name, Text tag, List<String> perms, List<Player> players) {
		this.name=name;
		this.tag=tag;
		this.perms=perms;
		this.players=players;
	}
	public Text getTag(){
		return tag;
	}
	public String getName(){
		return name;
	}
	public List<String> getPerms(){
		return perms;
	}
	public List<Player> getPlayers(){
		return players;
	}
	public void addPlayer(Player p){
		if(!players.contains(p)){
			players.add(p);
			for(String perm: perms){
				PermPlayer.givePerm(p, perm);
			}
			
		}
	}
	public void removePlayer(Player p){
		if(players.contains(p)){
			players.remove(p);
			for(String perm: perms){
				PermPlayer.takePerm(p, perm);
			}
		}
	}

}
