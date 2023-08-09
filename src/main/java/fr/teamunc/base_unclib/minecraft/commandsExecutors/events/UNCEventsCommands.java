package fr.teamunc.base_unclib.minecraft.commandsExecutors.events;

import fr.teamunc.base_unclib.BaseLib;
import fr.teamunc.base_unclib.models.libtools.CommandsTab;
import fr.teamunc.base_unclib.utils.helpers.Message;
import lombok.var;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UNCEventsCommands extends CommandsTab implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!BaseLib.isInit()) {
            Message.Get().sendMessage("BaseLib is not initialized!", sender, true);
            return false;
        }
        if (args.length < 2) {
            sender.sendMessage("Usage: /unce <start|stop> <event>");
            return false;
        }

        switch (args[0]) {
            case "start": {
                var event = BaseLib.getUNCEventController().getEvent(args[1]);
                if (event == null) {
                    sender.sendMessage("Event not found");
                    return false;
                }

                BaseLib.getUNCEventController().startEvent(event);
                break;
            }
            case "stop": {
                var event = BaseLib.getUNCEventController().getEvent(args[1]);
                if (event == null) {
                    sender.sendMessage("Event not found");
                    return false;
                }

                BaseLib.getUNCEventController().stopEvent(event);
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