package me.spypat.servercore.rank;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;


public class RankSetup{
	static Rank owner;
	static Rank admin;
	static Rank mod;
	static Rank officer;
	
	public static void setup(){
		List<String> ownerPerms = new ArrayList<String>();
		List<String> adminPerms = new ArrayList<String>();
		List<String> modPerms = new ArrayList<String>();
		List<String> officerPerms = new ArrayList<String>();
		List<Player> ownerPlayers = new ArrayList<Player>();
		List<Player> adminPlayers = new ArrayList<Player>();
		List<Player> modPlayers = new ArrayList<Player>();
		List<Player> officerPlayers = new ArrayList<Player>();
		ownerPerms.add("staff.eco");
		ownerPerms.add("staff.mute");
		ownerPerms.add("staff.broadcast");
		ownerPerms.add("staff.chat");
		ownerPerms.add("admin.chat");
		ownerPerms.add("staff.kick");
		ownerPerms.add("staff.tempban");
		ownerPerms.add("staff.ban");
		adminPerms.add("staff.eco");
		adminPerms.add("staff.mute");
		adminPerms.add("staff.broadcast");
		adminPerms.add("staff.chat");
		adminPerms.add("admin.chat");
		adminPerms.add("staff.kick");
		adminPerms.add("staff.tempban");
		modPerms.add("staff.mute");
		modPerms.add("staff.chat");
		modPerms.add("staff.kick");
		officerPerms.add("staff.chat");
		owner = new Rank("Owner", Text.of(TextColors.GRAY, "[", TextColors.RED, TextStyles.OBFUSCATED, "111", TextStyles.RESET, TextColors.BLUE, "Owner", TextColors.RED, TextStyles.OBFUSCATED, "111", TextStyles.NONE,TextColors.GRAY, "]"), ownerPerms, ownerPlayers);
		admin = new Rank("Admin", Text.of(TextColors.GRAY, "[", TextColors.RED, "Admin",TextColors.GRAY, "]"), adminPerms, adminPlayers);
		mod = new Rank("Mod", Text.of(TextColors.GRAY, "[", TextColors.GREEN, "Mod",TextColors.GRAY, "]"), modPerms, modPlayers);
		officer = new Rank("Officer", Text.of(TextColors.GRAY, "[", TextColors.AQUA, "Officer",TextColors.GRAY, "]"), officerPerms, officerPlayers);
	}
	public static void addPlayer(String s, Player p){
		if(s.equalsIgnoreCase("owner")){
			if(!owner.getPlayers().contains(p)){
				owner.addPlayer(p);
			}
		}
		if(s.equalsIgnoreCase("admin")){
			if(!admin.getPlayers().contains(p))
				admin.addPlayer(p);
		}
		if(s.equalsIgnoreCase("mod")){
			if(!mod.getPlayers().contains(p))
				mod.addPlayer(p);
		}
		if(s.equalsIgnoreCase("officer")){
			if(!officer.getPlayers().contains(p))
				officer.addPlayer(p);
		}
	}
	public static void removePlayer(String s, Player p){
		if(s.equalsIgnoreCase("owner")){
			if(owner.getPlayers().contains(p))
				owner.removePlayer(p);;
		}
		if(s.equalsIgnoreCase("admin")){
			if(admin.getPlayers().contains(p))
				admin.removePlayer(p);;
		}
		if(s.equalsIgnoreCase("mod")){
			if(mod.getPlayers().contains(p))
				mod.removePlayer(p);;
		}
		if(s.equalsIgnoreCase("officer")){
			if(officer.getPlayers().contains(p))
				officer.removePlayer(p);;
		}
	}
	public static String getRank(Player p){
		if(owner.getPlayers().contains(p))
			return "Owner";
		if(admin.getPlayers().contains(p))
			return "Admin";
		if(mod.getPlayers().contains(p))
			return "Mod";
		if(officer.getPlayers().contains(p))
			return "Officer";
		return "None";
	}
	public static Text getPrefix(String s){
		if(s=="Owner"||s=="owner"){
			return owner.getTag();
		}
		if(s=="Admin"||s=="admin"){
			return admin.getTag();
		}
		if(s=="Mod"||s=="mod"){
			return mod.getTag();
		}
		if(s=="Officer"||s=="officer"){
			return officer.getTag();
		}
		return Text.of("None");
	}
}
