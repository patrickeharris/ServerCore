package me.spypat.servercore.currency;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class TokenBalance implements CommandExecutor{

	public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
		if(context.hasAny("player")==true){
			Player p = (Player) context.getOne("player").get();
			source.sendMessage(Text.of(TextColors.GRAY, "[",TextColors.GREEN,"Token Balance Of ", p.getName(), TextColors.GRAY,"] : ",Currency.getTokens(p)));
			return CommandResult.builder().successCount(1).build();
		}
		if(!(source instanceof Player)){
			source.sendMessage(Text.of("Provide A Player!"));
			return CommandResult.builder().successCount(0).build();
		}
		Player player = (Player) source;
		source.sendMessage(Text.of(TextColors.GRAY, "[",TextColors.GREEN,"Token Balance",TextColors.GRAY,"] : ",Currency.getTokens(player)));
		return CommandResult.success();
	}

}
