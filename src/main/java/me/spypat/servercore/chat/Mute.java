package me.spypat.servercore.chat;

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

public class Mute implements CommandExecutor{

	static Map<Player, Long> muted = new HashMap<Player, Long>();
	
	public CommandResult execute(CommandSource sender, CommandContext args) throws CommandException {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(!PermPlayer.hasPerm(p, "staff.mute")){
				p.sendMessage(Text.of(TextColors.RED,"You Don't Have Permission To Do This!"));
				return CommandResult.builder().successCount(5).build();
			}
		}
		Player p = (Player) args.getOne("muting").get();
		int i = (Integer) args.getOne("time").get();
		long time = System.currentTimeMillis()+i;
		muted.put(p, time);
		
		return CommandResult.success();
	}
	public static boolean isStillMuted(Player p){
		if(!muted.containsKey(p))
			return false;
		if(System.currentTimeMillis()>muted.get(p))
			return false;
		return true;
	}



}
