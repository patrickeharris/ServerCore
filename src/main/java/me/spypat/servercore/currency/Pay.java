package me.spypat.servercore.currency;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class Pay implements CommandExecutor{

	public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
		int i = (Integer) context.getOne("amount").get();
		Player p = (Player) context.getOne("to").get();
		if(!(source instanceof Player)){
			source.sendMessage(Text.of("You Must Be A Player To Preform This Command!"));
			return CommandResult.builder().successCount(0).build();
		}
		Player player = (Player) source;
		if(Currency.getCoins(player)<i){
			player.sendMessage(Text.of("You Do Not Have Enough Coins!"));
			return CommandResult.builder().successCount(1).build();
		}
		Currency.takeCoins(player, i);
		Currency.giveCoins(p, i);
		return CommandResult.success();
	}

}
