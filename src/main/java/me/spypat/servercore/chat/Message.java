package me.spypat.servercore.chat;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Message implements CommandExecutor{

	public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
		String s = (String) context.getOne("message").get();
		Player p = (Player) context.getOne("to").get();
		p.sendMessage(Text.of(TextColors.GOLD, "[",source.getName(),"] : ",s));
		p.playSound(SoundTypes.ORB_PICKUP, p.getLocation().getPosition(), 3);
		return CommandResult.success();
	}

}
