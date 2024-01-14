package com.pastrymaker_studios.dev.criterion.module.quickie;

import com.pastrymaker_studios.dev.criterion.module.base.CriterionCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class CommandEntry extends Command {
    Map<String, CriterionCommand> children;

    public CommandEntry(String baseCmd) {
        super();
        this.children = children;
    }

    /**
     * Executes the command, returning its success
     *
     * @param sender       Source object which is executing this command
     * @param commandLabel The alias of the command used
     * @param args         All arguments passed to the command, split via ' '
     * @return true if the command was successful, otherwise false
     */
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        return false;
    }


}
