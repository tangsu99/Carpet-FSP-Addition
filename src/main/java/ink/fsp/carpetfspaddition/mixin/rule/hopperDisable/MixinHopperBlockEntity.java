package ink.fsp.carpetfspaddition.mixin.rule.hopperDisable;

import ink.fsp.carpetfspaddition.FspSettings;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlockEntity.class)
public abstract class MixinHopperBlockEntity {
    @Inject(method = "insert", at = @At("HEAD"), cancellable = true)
    private static void onInsert(World world, BlockPos pos, HopperBlockEntity blockEntity, CallbackInfoReturnable<Boolean> cir) {
        if (FspSettings.hopperDisable) {
            cir.setReturnValue(false);
        }
    }
}
