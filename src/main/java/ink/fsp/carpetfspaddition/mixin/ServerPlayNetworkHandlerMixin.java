package ink.fsp.carpetfspaddition.mixin;

import ink.fsp.carpetfspaddition.event.rule.itemFrameAdvancedInteract.PlayerUseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net/minecraft/server/network/ServerPlayNetworkHandler$1")
public abstract class ServerPlayNetworkHandlerMixin implements PlayerInteractEntityC2SPacket.Handler {
    @Shadow
    @Final
    ServerPlayNetworkHandler field_28963;

    @Shadow
    @Final
    Entity field_28962;

    @Inject(method = "interact(Lnet/minecraft/util/Hand;)V", at = @At(value = "HEAD"), cancellable = true)
    public void onPlayerInteractEntity(Hand hand, CallbackInfo ci) {
        PlayerEntity player = field_28963.player;
        World world = player.getEntityWorld();
        ActionResult result = PlayerUseEntityCallback.EVENT.invoker().onPlayerUseEntity(player, world, hand, field_28962, null);

        if (result != ActionResult.PASS) {
            ci.cancel();
        }
    }
}
