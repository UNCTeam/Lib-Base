package fr.teamunc.base_unclib.models.libtools;

import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CommandsTab implements TabCompleter {

    @SafeVarargs
    protected final List<String> checkAllTab(String[] args, List<String>... allListsToTest) {

        String arg = args[args.length-1];
        List<String> listToTest = allListsToTest[args.length-1];

        return checkOneTabulationFor(arg, listToTest);
    }

    private List<String> checkOneTabulationFor(String arg, List<String> list) {
        final List<String> completions = new ArrayList<>();
        //copy matches of first argument from list (ex: if first arg is 'm' will return just 'minecraft')
        StringUtil.copyPartialMatches(arg, list, completions);
        //sort the list
        Collections.sort(completions);
        return completions;
    }
}

