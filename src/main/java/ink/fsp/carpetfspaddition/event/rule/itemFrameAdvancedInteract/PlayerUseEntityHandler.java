package ink.fsp.carpetfspaddition.event.rule.itemFrameAdvancedInteract;

import ink.fsp.carpetfspaddition.FspSettings;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PlayerUseEntityHandler implements PlayerUseEntityCallback {
    @Override
    public ActionResult onPlayerUseEntity(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!FspSettings.itemFrameAdvancedInteract) {
            return ActionResult.PASS;
        }
        if (entity instanceof ItemFrameEntity) {
            if (!player.isSneaking() && !player.isSpectator() && !((ItemFrameEntity) entity).getHeldItemStack().isEmpty()) {
                int x, y, z;
                x = entity.getBlockX();
                y = entity.getBlockY();
                z = entity.getBlockZ();
                return switch (entity.getFacing()) {
                    case NORTH -> openChest(x, y, z + 1, world, player);
                    case SOUTH -> openChest(x, y, z - 1, world, player);
                    case WEST -> openChest(x + 1, y, z, world, player);
                    case EAST -> openChest(x - 1, y, z, world, player);
                    case DOWN -> openChest(x, y + 1, z, world, player);
                    case UP -> openChest(x, y - 1, z, world, player);
                };
            }
        }
        return ActionResult.PASS;
    }

    private static ActionResult openChest(int x, int y, int z, World world, PlayerEntity player) {
        BlockPos blockPos = new BlockPos(x, y, z);
        BlockState blockState = world.getBlockState(new BlockPos(x, y, z));
        NamedScreenHandlerFactory namedScreenHandlerFactory = blockState.createScreenHandlerFactory(world, blockPos);
        if (namedScreenHandlerFactory != null) {
            player.openHandledScreen(namedScreenHandlerFactory);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
