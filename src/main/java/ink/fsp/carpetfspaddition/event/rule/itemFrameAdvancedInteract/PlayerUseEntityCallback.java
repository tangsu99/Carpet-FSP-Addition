package ink.fsp.carpetfspaddition.event.rule.itemFrameAdvancedInteract;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public interface PlayerUseEntityCallback {
    Event<PlayerUseEntityCallback> EVENT = EventFactory.createArrayBacked(PlayerUseEntityCallback.class,
            (listeners) -> (player, world, hand, entity, hitResult) -> {
                for (PlayerUseEntityCallback listener : listeners) {
                    ActionResult result = listener.onPlayerUseEntity(player, world, hand, entity, hitResult);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    ActionResult onPlayerUseEntity(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult);
}
