package ink.fsp.carpetfspaddition.mixin.rule.hopperDisable;

import ink.fsp.carpetfspaddition.FspSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BooleanSupplier;

@Mixin(HopperBlockEntity.class)
public abstract class MixinHopperBlockEntity {

    @Inject(
            method = "insertAndExtract",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/entity/HopperBlockEntity;needsCooldown()Z"
            ),
            cancellable = true
    )
    private static void onInsertAndExtract(World world, BlockPos pos, BlockState state, HopperBlockEntity blockEntity, BooleanSupplier booleanSupplier, CallbackInfoReturnable<Boolean> cir) {
        if (FspSettings.hopperDisable) {
            cir.setReturnValue(false);
        }
    }
}
