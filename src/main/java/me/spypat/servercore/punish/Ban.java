package me.spypat.servercore.punish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackGenerator;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import me.spypat.servercore.perm.PermPlayer;
import me.spypat.servercore.rank.RankSetup;

public class Ban implements CommandExecutor{
		static List<Player>banned = new ArrayList<Player>();
		public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
			if(source instanceof Player){
				Player p = (Player) source;
				if(!PermPlayer.hasPerm(p, "staff.ban")){
					p.sendMessage(Text.of(TextColors.RED,"You Don't Have Permission To Do This!"));
					return CommandResult.builder().successCount(5).build();
				}
			}
			Player p = (Player) context.getOne("banning").get();
			if(RankSetup.getRank(p)=="Owner"||RankSetup.getRank(p)=="Admin"||RankSetup.getRank(p)=="Mod"){
				source.sendMessage(Text.of(TextColors.RED,"You Cannot Ban A Staff Member!"));
				return CommandResult.builder().successCount(0).build();
			}
			banned.add(p);
			p.kick(Text.of("You Have Been Banned For ",context.getOne("banreason").get()));
			return CommandResult.success();
		}
		public static boolean isStillBanned(Player p){
			if(!banned.contains(p))
				return false;
			return true;
		}
}
