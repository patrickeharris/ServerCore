package me.spypat.servercore.listeners;

import java.util.Optional;

import org.spongepowered.api.CatalogTypes;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.tileentity.SignData;
import org.spongepowered.api.data.value.mutable.ListValue;
import org.spongepowered.api.entity.Item;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;

import me.spypat.servercore.currency.Currency;

public class InteractEntity {

	@Listener
	public void onInteractBlock(InteractBlockEvent e){
		if(e.getCause().root() instanceof Player){
			Player p = (Player)e.getCause().root();
			BlockState state = e.getTargetBlock().getExtendedState();
			if(state.getType().equals(BlockTypes.WALL_SIGN)){
				Location l = e.getTargetBlock().getLocation().get();
				TileEntity te = (TileEntity) l.getTileEntity().get();
				ListValue<Text> signLines = te.getValue(Keys.SIGN_LINES).get();
				Text t = signLines.get(0);
				if(t.toPlain().equalsIgnoreCase("shop")){
					Text t2 = signLines.get(3);
					ItemType i = Sponge.getRegistry().getType(ItemType.class, t2.toPlain()).get();
					ItemStack is = i.getTemplate().createStack();
					Text t3 = signLines.get(2);
					int integer = Integer.parseInt(t3.toPlain());
					is.setQuantity(integer);
					Text t4 = signLines.get(1);
					int integer2 = Integer.parseInt(t4.toPlain());
					if(Currency.getCoins(p)>=integer2){
						Currency.takeCoins(p, integer2);
						if(!p.getInventory().isEmpty()){
							p.getInventory().offer(is);
							p.sendMessage(Text.of("Succsessfully Bought Item!"));
						}
					}else
						p.sendMessage(Text.of("You Don't Have Enough Coins!"));					
				}
			}
		}
	     
	}
}
