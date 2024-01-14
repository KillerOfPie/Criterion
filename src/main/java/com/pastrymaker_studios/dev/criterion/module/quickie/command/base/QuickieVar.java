package com.pastrymaker_studios.dev.criterion.module.quickie.command.base;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

public class QuickieVar implements QuickieCMDNode {

    @Getter
    @Setter(value = AccessLevel.PROTECTED) // Setter for use only in builder
    QuickieVarType type;
    @Setter(AccessLevel.PROTECTED) // Setter for use only in builder
    private boolean optional = false;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @Getter(onMethod_ = {@Override})
    private Optional<QuickieCMDNode> parsedNode;
    @Getter(onMethod_ = {@Override})
    @Setter(value = AccessLevel.PROTECTED) // Setter for use only in builder
    private String description, usage, key;
    @Getter
    @Setter(value = AccessLevel.PROTECTED) // Setter for use only in builder
    private int position; // -1 if the primary command, position starting at 0 for args

    public QuickieVar(QuickieVarType type, int position) {
        this.type = type;
        this.position = position;
    }

    public QuickieVar(QuickieVarType type, String key) {
        this.type = type;
        this.key = key;
    }

    /**
     * @param command
     * @return
     */
    @Override
    public Optional<QuickieCMDNode> parse(QuickieSentCommand command) {
        if (usesKey().isPresent()) {
            Optional<Integer> keyPos = command.getPosOfKey(usesKey().get());

            if (keyPos.isPresent()) {
                Optional<String> argval = command.getArgAtPos(keyPos.get() + 1);
                return argval.isPresent() ?;
            }


        } else {
            if (command.getArgs().size() > position) {
                return type.read(command.getArgs().get(position));
            }
        }

        parsedNode = Optional.empty(); //Reset before each parse to insure new values

        Optional<String> key = usesKey();
        if (key.isPresent()) {
            if (!command.getPosOfKey(key.get()).isPresent()) return Optional.empty();

            Optional<String> pos = command.getArgAtPos(getPosition());
            if (!pos.isPresent() || !type.validate(pos.get()) {
                if (!isOptional()) return Optional.empty();
            }
        }
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
        return false;
    }

    @Override
    public Optional<String> usesKey() {
        return key == null ? Optional.empty() : Optional.of(key);
    }
}
