package me.spypat.servercore.listeners;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.network.PlayerConnection;
import org.spongepowered.api.text.Text;

import me.spypat.servercore.punish.Ban;
import me.spypat.servercore.punish.TempBan;

public class PlayerJoin {

	@Listener
	public void onPlayerJoin(PlayerConnection event){
		if(TempBan.isStillBanned(event.getPlayer())==true){
			Long l = TempBan.getBanTime(event.getPlayer());
			Long l2 = l/60000;
			event.getPlayer().kick(Text.of("You Are Still TempBanned For ",l2, " minutes!"));
		}
		if(Ban.isStillBanned(event.getPlayer())==true){
			event.getPlayer().kick(Text.of("You Have Been Banned!"));
		}
	}
}
