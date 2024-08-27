package ink.fsp.carpetfspaddition.mixin;

import ink.fsp.carpetfspaddition.CarpetFspAdditionServer;
import ink.fsp.carpetfspaddition.FspSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnHelper.class)
public abstract class MixinSpawnHelper {
    @Inject(method = "createMob", at = @At("HEAD"), cancellable = true)
    private static void onCreateMob(ServerWorld world, EntityType<?> type, CallbackInfoReturnable<MobEntity> cir) {
        if (!FspSettings.customStopSpawnMob.equals("#none")) {
            if (CarpetFspAdditionServer.customStopSpawnMobList.contains(EntityType.getId(type).toString())){
                cir.setReturnValue(null);
            }
        }
    }
}
