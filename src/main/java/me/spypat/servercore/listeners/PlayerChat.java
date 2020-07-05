package me.spypat.servercore.listeners;

import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import me.spypat.servercore.chat.AdminChat;
import me.spypat.servercore.chat.Mute;
import me.spypat.servercore.chat.StaffChat;
import me.spypat.servercore.rank.RankSetup;

public class PlayerChat {

	@Listener
	public void onPlayerChat(MessageChannelEvent e){
		Optional<Player> playerOptional = e.getCause().<Player>first(Player.class);
		if(!playerOptional.isPresent()) return;
	    Player player = playerOptional.get();
	    if(Mute.isStillMuted(player)==true){
	    	player.sendMessage(Text.of("You Cannot Speak When Muted!"));
	    	e.setChannel(MessageChannel.TO_NONE);
	    	return;
	    }
	    if(StaffChat.containsSC(player)==true){
	    	for(Player p : Sponge.getGame().getServer().getOnlinePlayers()){
	    		if(StaffChat.containsSC(p)==true){
	    			p.sendMessage(Text.of(TextColors.GRAY, TextStyles.BOLD, e.getMessage()));
	    			p.playSound(SoundTypes.LEVEL_UP, p.getLocation().getPosition(), 3);
	    		}
	    	}
	    	e.setChannel(MessageChannel.TO_NONE);
	    }
	    if(AdminChat.containsSC(player)==true){
	    	for(Player p : Sponge.getGame().getServer().getOnlinePlayers()){
	    		if(AdminChat.containsSC(p)==true){
	    			p.sendMessage(Text.of(TextColors.RED, TextStyles.BOLD, e.getMessage()));
	    			p.playSound(SoundTypes.LEVEL_UP, p.getLocation().getPosition(), 3);
	    		}
	    	}
	    	e.setChannel(MessageChannel.TO_NONE);
	    }
	    String s = RankSetup.getRank(player);
	    if(s!="None"){
	    	Text t = RankSetup.getPrefix(s);
	    	Text text = e.getMessage();
	    	e.setMessage(Text.of(t, " : ", text));
	    }

	}


}
