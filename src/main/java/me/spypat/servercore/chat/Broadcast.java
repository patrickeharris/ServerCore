package me.spypat.servercore.chat;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import me.spypat.servercore.perm.PermPlayer;

public class Broadcast implements CommandExecutor{

	public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
		if(source instanceof Player){
			Player p = (Player)source;
			if(!PermPlayer.hasPerm(p, "staff.broadcast")){
				p.sendMessage(Text.of(TextColors.RED,"You Don't Have Permission To Do This!"));
				return CommandResult.builder().successCount(5).build();
			}
		}
		String s = (String) context.getOne("message").get();
		Sponge.getGame().getServer().getBroadcastChannel().TO_ALL.send(Text.of(TextColors.GRAY, "[",TextColors.BLUE,"Broadcast",TextColors.GRAY, "] : ",s));
		return CommandResult.success();
	}

}
