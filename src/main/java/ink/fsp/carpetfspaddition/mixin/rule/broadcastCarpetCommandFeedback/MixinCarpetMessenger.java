package ink.fsp.carpetfspaddition.mixin.rule.broadcastCarpetCommandFeedback;

import carpet.utils.Messenger;
import ink.fsp.carpetfspaddition.FspSettings;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Messenger.class)
public abstract class MixinCarpetMessenger {
    @Inject(
            method = "m(Lnet/minecraft/server/command/ServerCommandSource;[Ljava/lang/Object;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/command/ServerCommandSource;sendFeedback(Ljava/util/function/Supplier;Z)V"
            ),
            cancellable = true
    )
    private static void onM(ServerCommandSource source, Object[] fields, CallbackInfo ci) {
        if (FspSettings.broadcastCarpetCommandFeedback == FspSettings.broadcastCarpetCommandFeedbackEnum.ALL) {
            return;
        }
        if (FspSettings.broadcastCarpetCommandFeedback == FspSettings.broadcastCarpetCommandFeedbackEnum.SELF) {
            source.sendMessage(Messenger.c(fields));
            ci.cancel();
        }
    }
}
