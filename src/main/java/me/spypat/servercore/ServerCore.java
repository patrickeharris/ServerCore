package me.spypat.servercore;

import org.spongepowered.api.Game;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import com.google.inject.Inject;

import me.spypat.servercore.chat.AdminChat;
import me.spypat.servercore.chat.Broadcast;
import me.spypat.servercore.chat.Message;
import me.spypat.servercore.chat.Mute;
import me.spypat.servercore.chat.StaffChat;
import me.spypat.servercore.currency.CoinBalance;
import me.spypat.servercore.currency.GiveCoins;
import me.spypat.servercore.currency.GiveTokens;
import me.spypat.servercore.currency.Pay;
import me.spypat.servercore.currency.TakeCoins;
import me.spypat.servercore.currency.TakeTokens;
import me.spypat.servercore.currency.TokenBalance;
import me.spypat.servercore.listeners.InteractEntity;
import me.spypat.servercore.listeners.PlayerChat;
import me.spypat.servercore.listeners.PlayerJoin;
import me.spypat.servercore.punish.Ban;
import me.spypat.servercore.punish.Kick;
import me.spypat.servercore.punish.TempBan;
import me.spypat.servercore.rank.Add;
import me.spypat.servercore.rank.RankSetup;
import me.spypat.servercore.rank.Remove;

@Plugin(id = "me.spypat.servercore", name = "ServerCore", version = "1.1.9")
public class ServerCore {
	
	@Inject
	public Game game;
	
	@Listener
	public void onServerStart(GameInitializationEvent e){
		//Load Players From Database And Give Players Perms
		RankSetup.setup();
		
		CommandSpec staffchat = CommandSpec.builder()
				.description(Text.of("Enter Staff Chat!"))
				.arguments(GenericArguments.none())
				.executor(new StaffChat())
				.build();
		CommandSpec adminchat = CommandSpec.builder()
				.description(Text.of("Enter Admin Chat!"))
				.arguments(GenericArguments.none())
				.executor(new AdminChat())
				.build();
		CommandSpec mute = CommandSpec.builder()
				.description(Text.of("Mute A Player!"))
				.arguments(GenericArguments.player(Text.of("muting")),GenericArguments.integer(Text.of("time")))
				.executor(new Mute())
				.build();
		CommandSpec broadcast = CommandSpec.builder()
				.description(Text.of("Broadcast!"))
				.arguments(GenericArguments.remainingJoinedStrings(Text.of("broadcast")))
				.executor(new Broadcast())
				.build();
		CommandSpec message = CommandSpec.builder()
				.description(Text.of("Message A Player!"))
				.arguments(GenericArguments.player(Text.of("to")),GenericArguments.remainingJoinedStrings(Text.of("message")))
				.executor(new Message())
				.build();
		CommandSpec pay = CommandSpec.builder()
				.description(Text.of("Pay A Player!"))
				.arguments(GenericArguments.player(Text.of("to")),GenericArguments.integer(Text.of("amount")))
				.executor(new Pay())
				.build();
		CommandSpec giveCoins = CommandSpec.builder()
				.description(Text.of("Give A Player Coins!"))
				.arguments(GenericArguments.player(Text.of("to")),GenericArguments.integer(Text.of("amount")))
				.executor(new GiveCoins())
				.build();
		CommandSpec giveTokens = CommandSpec.builder()
				.description(Text.of("Give A Player Tokens!"))
				.arguments(GenericArguments.player(Text.of("to")),GenericArguments.integer(Text.of("amount")))
				.executor(new GiveTokens())
				.build();
		CommandSpec takeCoins = CommandSpec.builder()
				.description(Text.of("Remove A Player's Coins!"))
				.arguments(GenericArguments.player(Text.of("to")),GenericArguments.integer(Text.of("amount")))
				.executor(new TakeCoins())
				.build();
		CommandSpec takeTokens = CommandSpec.builder()
				.description(Text.of("Remove A Player's Tokens!"))
				.arguments(GenericArguments.player(Text.of("to")),GenericArguments.integer(Text.of("amount")))
				.executor(new TakeTokens())
				.build();
		CommandSpec coinBalance = CommandSpec.builder()
				.description(Text.of("Check Your Amount of Coins!"))
				.arguments(GenericArguments.optional(GenericArguments.player(Text.of("player"))))
				.executor(new CoinBalance())
				.build();
		CommandSpec tokenBalance = CommandSpec.builder()
				.description(Text.of("Check Your Amount of Tokens!"))
				.arguments(GenericArguments.optional(GenericArguments.player(Text.of("player"))))
				.executor(new TokenBalance())
				.build();
		CommandSpec addRank = CommandSpec.builder()
				.description(Text.of("Add A Player To A Rank!"))
				.arguments(GenericArguments.player(Text.of("adding")), GenericArguments.string(Text.of("rank")))
				.executor(new Add())
				.build();
		CommandSpec removeRank = CommandSpec.builder()
				.description(Text.of("Remove A Player From A Rank!"))
				.arguments(GenericArguments.player(Text.of("removing")), GenericArguments.string(Text.of("rankremove")))
				.executor(new Remove())
				.build();
		CommandSpec kick = CommandSpec.builder()
				.description(Text.of("Kick A Player!"))
				.arguments(GenericArguments.player(Text.of("kicking")), GenericArguments.string(Text.of("reason")))
				.executor(new Kick())
				.build();
		CommandSpec tempBan = CommandSpec.builder()
				.description(Text.of("TempBan A Player!"))
				.arguments(GenericArguments.player(Text.of("tempbanning")), GenericArguments.doubleNum(Text.of("bantime")),GenericArguments.string(Text.of("tempbanreason")))
				.executor(new TempBan())
				.build();
		CommandSpec ban = CommandSpec.builder()
				.description(Text.of("Ban A Player!"))
				.arguments(GenericArguments.player(Text.of("banning")), GenericArguments.string(Text.of("banreason")))
				.executor(new Ban())
				.build();
		game.getCommandManager().register(this, kick, "kick");
		game.getCommandManager().register(this, tempBan, "tempban");
		game.getCommandManager().register(this, ban, "ban");
		game.getCommandManager().register(this, addRank, "addrank", "ar", "giverank", "gr");
		game.getCommandManager().register(this, removeRank, "removerank", "rr", "takerank", "tr");
		game.getCommandManager().register(this, coinBalance, "coinbalance", "cb");
		game.getCommandManager().register(this, tokenBalance, "tokenbalance", "tb");
		game.getCommandManager().register(this, staffchat, "staffchat", "sc");
		game.getCommandManager().register(this, pay, "pay");
		game.getCommandManager().register(this, giveCoins, "givecoins", "gc");
		game.getCommandManager().register(this, giveTokens, "givetokens", "gt");
		game.getCommandManager().register(this, takeCoins, "takecoins", "removecoins", "tc", "rc");
		game.getCommandManager().register(this, takeTokens, "taketokens", "removetokens", "tt", "rt");
		game.getCommandManager().register(this, broadcast, "broadcast", "bc");
		game.getCommandManager().register(this, adminchat, "adminchat", "ac");
		game.getCommandManager().register(this, mute, "mute", "silence");
		game.getCommandManager().register(this, message, "message", "msg", "tell", "whisper");
		game.getEventManager().registerListeners(this, new PlayerChat());
		game.getEventManager().registerListeners(this, new PlayerJoin());
		game.getEventManager().registerListeners(this, new InteractEntity());
		
	}

	
}
