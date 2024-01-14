package com.pastrymaker_studios.dev.criterion.module.quickie.command.base;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
public class QuickieSentCommand {

    CommandSender sender;
    Command cmd;
    String label;
    List<String> args;

    public QuickieSentCommand(CommandSender sender, Command cmd, String label, String[] args) {
        this.sender = sender;
        this.cmd = cmd;
        this.label = label;
        this.args = Arrays.asList(args);
    }

    public Optional<String> getArgAtPos(int position) {
        // if position is greater than the size of the args list, return empty (out of bounds)
        if(position >= args.size()) return Optional.empty();

        return Optional.of(position < 0 ? label : args.get(position));
    }

    public Optional<Integer> getPosOfKey(String key) {
        for(int i = 0; i < args.size(); i++) {
            if(args.get(i).equalsIgnoreCase(key)) {
                return Optional.of(i);
            }
        }

        return Optional.empty();
    }

}
