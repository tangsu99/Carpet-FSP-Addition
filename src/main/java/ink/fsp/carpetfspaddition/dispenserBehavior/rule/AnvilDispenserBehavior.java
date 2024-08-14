package ink.fsp.carpetfspaddition.dispenserBehavior.rule;

import ink.fsp.carpetfspaddition.FspSettings;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.util.math.Position;

public class AnvilDispenserBehavior implements DispenserBehavior {
    @Override
    public ItemStack dispense(BlockPointer pointer, ItemStack stack) {
        Direction direction = pointer.state().get(DispenserBlock.FACING);
        BlockPos placePos = pointer.pos().offset(direction);
        if (pointer.world().getBlockState(placePos).isAir() && FspSettings.anvilDispenserBehavior) {
            stack.decrement(1);
            FallingBlockEntity.spawnFromBlock(pointer.world(), placePos, Block.getBlockFromItem(Items.ANVIL).getDefaultState());
            return stack;
        }
        Position position = DispenserBlock.getOutputLocation(pointer);
        ItemStack itemStack = stack.split(1);
        ItemDispenserBehavior.spawnItem(pointer.world(), itemStack, 6, direction, position);
        return stack;
    }
}
