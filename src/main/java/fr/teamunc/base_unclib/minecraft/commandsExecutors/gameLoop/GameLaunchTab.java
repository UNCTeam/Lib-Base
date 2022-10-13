package fr.teamunc.base_unclib.minecraft.commandsExecutors.gameLoop;

import fr.teamunc.base_unclib.models.libtools.CommandsTab;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.Collections;
import java.util.List;

public class GameLaunchTab extends CommandsTab {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> result = checkAllTab(
                args,
                GameLaunchCommands.getCommands());

        //sort the list
        Collections.sort(result);

        return result;
    }
}
