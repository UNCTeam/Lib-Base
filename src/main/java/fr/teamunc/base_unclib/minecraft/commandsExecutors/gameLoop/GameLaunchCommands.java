package fr.teamunc.base_unclib.minecraft.commandsExecutors.gameLoop;

import fr.teamunc.base_unclib.BaseLib;
import fr.teamunc.base_unclib.utils.helpers.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GameLaunchCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("uncgame")) {
            return false;
        }

        if (!BaseLib.IsInit()) {
            Message.Get().sendMessage("BaseLib is not initialized!", sender, true);
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("Usage: /uncgame <start|stop|nextphase>");
            return true;
        }

        switch (args[0]) {
            case "start": {
                BaseLib.getUNCPhaseController().startGame();
                return true;
            }
            case "stop": {
                BaseLib.getUNCPhaseController().stopGame();
                return true;
            }
            case "nextphase": {
                BaseLib.getUNCPhaseController().nextPhase();
                return true;
            }
        }

        return true;
    }
}
