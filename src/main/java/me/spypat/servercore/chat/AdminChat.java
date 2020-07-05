package me.spypat.servercore.chat;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import me.spypat.servercore.perm.PermPlayer;

public class AdminChat implements CommandExecutor{
	static List<String> staffchat = new ArrayList<String>();

	public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
		if(!(source instanceof Player)){
			source.sendMessage(Text.of("You Must Be A Player To Enter Admin Chat!"));
			return CommandResult.builder().successCount(0).build();
		}
		Player p = (Player) source;
		if(!PermPlayer.hasPerm(p, "admin.chat")){
			p.sendMessage(Text.of(TextColors.RED,"You Don't Have Permission To Do This!"));
			return CommandResult.builder().successCount(5).build();
		}
		if(staffchat.contains(p.getName())){
			staffchat.remove(p.getName());
			p.sendMessage(Text.of("You Have Exited Admin Chat!"));
			return CommandResult.builder().successCount(1).build();
		}
		staffchat.add(p.getName());
		p.sendMessage(Text.of("You Have Entered Admin Chat!"));
		return CommandResult.success();
	}
	
	public static void addSC(Player p){
		staffchat.add(p.getName());
	}
	public static boolean containsSC(Player p){
		if(staffchat.contains(p.getName()))
			return true;
		return false;
	}
	public static void revoveSC(Player p){
		if(staffchat.contains(p.getName()))
			staffchat.remove(p.getName());
	}
}
