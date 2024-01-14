package com.pastrymaker_studios.dev.criterion.module.quickie.command.base;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuickieCommand implements QuickieCMDNode {

    private final String command;
    @Setter(AccessLevel.PROTECTED) // Setter for use only in builder
    private List<String> aliases;
    @Setter(AccessLevel.PROTECTED) // Setter for use only in builder
    private List<QuickieCMDNode> cmdNodes;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @Getter(onMethod_ = {@Override})
    private Optional<QuickieCMDNode> parsedNode;
    @Getter(onMethod_ = {@Override})
    private List<Optional<QuickieCMDNode>> parsedNodes;
    @Setter(AccessLevel.PROTECTED) // Setter for use only in builder
    private boolean optional = false;
    @Getter(onMethod_ = {@Override})
    @Setter(value = AccessLevel.PROTECTED) // Setter for use only in builder
    private String description, usage, key;
    @Getter
    @Setter(value = AccessLevel.PROTECTED) // Setter for use only in builder
    private int position; // -1 if the primary command, position starting at 0 for args

    public QuickieCommand(String cmd, int position) {
        this.command = cmd;
        this.position = position;
        aliases = Lists.newArrayList(cmd);
    }

    /**
     * Parse the command assuming
     *
     * @param command
     * @return
     */
    @Override
    public Optional<QuickieCMDNode> parse(QuickieSentCommand command) {
        parsedNodes = Lists.newArrayList(); //Reset before each parse to insure new values
        parsedNode = Optional.empty(); //Reset before each parse to insure new values

        Optional<String> key = usesKey();
        if (key.isPresent() && !command.getPosOfKey(key.get()).isPresent()) {
            if (!isOptional()) return Optional.empty();
        } else {
            if (!command.getArgAtPos(getPosition()).isPresent()) {
                if (!isOptional()) return Optional.empty();
            }
        }

        for (QuickieCMDNode node : cmdNodes) {
            Optional<QuickieCMDNode> parsed = node.parse(command);
            if (!parsed.isPresent() && !node.isOptional()) {
                return Optional.empty();
            }

            parsedNodes.add(parsed);
        }

        parsedNode = Optional.of(this);

        return parsedNode;
    }

    public boolean execute(Command command) {
        return false;
    }

    /**
     * @param current
     * @return Options for tab completion
     */
    @Override
    public List<String> getTabComplete(String current) {
        return null;
    }

    /**
     * @return true if the node is optional, otherwise false
     */
    @Override
    public boolean isOptional() {
        return optional;
    }

    @Override
    public Optional<String> usesKey() {
        return key == null ? Optional.empty() : Optional.of(key);
    }

    protected void addAliases(String... aliases) {
        Collections.addAll(this.aliases, aliases);
    }

    protected void addNodes(QuickieCMDNode... nodes) {
        for (int i = cmdNodes.size(); i < cmdNodes.size() + nodes.length; i++) {
            QuickieCMDNode node = nodes[i];
            node.setPosition(i);
            cmdNodes.add(node);
        }
    }
}

