package me.spypat.servercore.currency;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import me.spypat.servercore.perm.PermPlayer;

public class GiveCoins implements CommandExecutor{

	public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
		if(source instanceof Player){
			Player p = (Player)source;
			if(!PermPlayer.hasPerm(p, "staff.eco")){
				p.sendMessage(Text.of(TextColors.RED,"You Don't Have Permission To Do This!"));
				return CommandResult.builder().successCount(5).build();
			}
		}
		int i = (Integer) context.getOne("amount").get();
		Player p = (Player) context.getOne("to").get();
		Currency.giveCoins(p, i);
		source.sendMessage(Text.of("You Have Given ", p.getName(), " ", i, " Coins!"));
		return CommandResult.success();
	}

}
