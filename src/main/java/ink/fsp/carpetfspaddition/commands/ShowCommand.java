package ink.fsp.carpetfspaddition.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;


public class ShowCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("show").executes((ShowCommand::show)));
    }
    private static int show(CommandContext<ServerCommandSource> context){
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayer();
        if (player != null) {
            source.getServer().getPlayerManager().getPlayerList().forEach((serverPlayerEntity) -> serverPlayerEntity.sendMessage(
                    Text.literal(player.getName().getString())
                            .append(Text.of(" main hand: "))
                            .append(player.getMainHandStack().toHoverableText())
                            .append(Text.of("; off hand: "))
                            .append(player.getOffHandStack().toHoverableText())
            ));
        }
        return 1;
    }
}
