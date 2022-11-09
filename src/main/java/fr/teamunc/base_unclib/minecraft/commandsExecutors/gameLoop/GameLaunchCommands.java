package fr.teamunc.base_unclib.minecraft.commandsExecutors.gameLoop;

import fr.teamunc.base_unclib.BaseLib;
import fr.teamunc.base_unclib.models.libtools.CommandsTab;
import fr.teamunc.base_unclib.utils.helpers.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameLaunchCommands extends CommandsTab implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("uncgame")) {
            return false;
        }

        if (!BaseLib.isInit()) {
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
                break;
            }
            case "stop": {
                BaseLib.getUNCPhaseController().stopGame();
                break;
            }
            case "nextphase": {
                BaseLib.getUNCPhaseController().nextPhase();
                break;
            }
            default: {
                break;
            }
        }

        return true;
    }

    public static List<String> getCommands() {
        return new ArrayList<>(Arrays.asList("start", "stop", "nextphase"));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> result = checkAllTab(
                args,
                getCommands());

        //sort the list
        Collections.sort(result);

        return result;
    }
}
