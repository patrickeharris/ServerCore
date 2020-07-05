package me.spypat.servercore.listeners;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.value.mutable.ListValue;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class PlaceBlock {

	@Listener
	public void onPlaceBlock(ChangeBlockEvent.Place e){
		if(e.getCause().root() instanceof Player){
			Player p = (Player)e.getCause().root();
			BlockState state = e.getTransactions().get(0).getFinal().getState();
				if(state.getType().equals(BlockTypes.WALL_SIGN)){
					Location<World> l = e.getTransactions().get(0).getFinal().getLocation().get();
					TileEntity te = (TileEntity) l.getTileEntity().get();
					ListValue<Text> signLines = te.getValue(Keys.SIGN_LINES).get();
					Text t = signLines.get(0);
					if(t.toPlain().equalsIgnoreCase("pokestop")){
						if(p.getName().equalsIgnoreCase("spypat")){
							p.sendMessage(Text.of(TextColors.RED,"You Made A PokeStop!"));
							return;
						}else{
							p.sendMessage(Text.of(TextColors.RED,"You Cannot Make A PokeStop!"));
							e.setCancelled(true);
						}
					}
				}
		}
	}
}
