package me.spypat.servercore.rank;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class Add implements CommandExecutor{

	public CommandResult execute(CommandSource arg0, CommandContext arg1) throws CommandException {
		Player p = (Player) arg1.getOne("adding").get();
		String s = (String) arg1.getOne("rank").get();
		RankSetup.addPlayer(s, p);
		arg0.sendMessage(Text.of(p.getName(), " Has Been Added To ", s));
		return CommandResult.success();
	}

}
