package me.spypat.servercore.punish;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import me.spypat.servercore.perm.PermPlayer;
import me.spypat.servercore.rank.RankSetup;

public class TempBan implements CommandExecutor{
	static Map<Player, Long>tempbanned = new HashMap<Player, Long>();
	public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
		if(source instanceof Player){
			Player p = (Player) source;
			if(!PermPlayer.hasPerm(p, "staff.tempban")){
				p.sendMessage(Text.of(TextColors.RED,"You Don't Have Permission To Do This!"));
				return CommandResult.builder().successCount(5).build();
			}
		}
		Player p = (Player) context.getOne("tempbanning").get();
		if(RankSetup.getRank(p)=="Owner"||RankSetup.getRank(p)=="Admin"||RankSetup.getRank(p)=="Mod"){
			source.sendMessage(Text.of(TextColors.RED,"You Cannot Ban A Staff Member!"));
			return CommandResult.builder().successCount(0).build();
		}
		Double l = (Double)context.getOne("bantime").get();
		Long l4 = l.longValue();
		Long l2 = System.currentTimeMillis()+l4;
		tempbanned.put(p, l2);
		Long l3 = l4/60000;
		p.kick(Text.of("You Have Been Banned For ", l3, " Minutes Because ",context.getOne("tempbanreason").get()));
		return CommandResult.success();
	}
	public static boolean isStillBanned(Player p){
		if(!tempbanned.containsKey(p))
			return false;
		if(System.currentTimeMillis()>tempbanned.get(p))
			return false;
		return true;
	}
	public static Long getBanTime(Player p){
		return tempbanned.get(p);
	}
}

