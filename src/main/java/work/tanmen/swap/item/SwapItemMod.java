package work.tanmen.swap.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mod("swapitem")
public class SwapItemMod {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public SwapItemMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onPlayerDestroyItemEvent(final PlayerDestroyItemEvent event) {
        Optional<ItemStack> targetStack = event.getPlayer().getInventory()
                .items
                .stream()
                .filter(stack -> stack.is(event.getOriginal().getItem()))
                .findFirst();
        if (targetStack.isPresent()) {
            InteractionHand hand = Optional.ofNullable(event.getHand()).orElse(InteractionHand.MAIN_HAND);
            ItemStack stack = targetStack.get();
            int slot = event.getPlayer().getInventory().findSlotMatchingItem(stack);

            LOGGER.info("[SwapItem] Found swappable items '{}'", stack.getItem().getRegistryName());
            event.getPlayer().setItemInHand(hand, stack);
            event.getPlayer().getInventory().setItem(slot, ItemStack.EMPTY);
        }
    }
}
