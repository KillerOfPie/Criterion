package com.pastrymaker_studios.dev.criterion.module.quickie.command.base;

import java.util.List;
import java.util.Optional;

public interface QuickieCMDNode {

    Optional<QuickieCMDNode> parse(QuickieSentCommand command);

    int getPosition();
    void setPosition(int position);

    /**
     * @return Options for tab completion
     */
    List<String> getTabComplete(String current);

    /**
     * Provides the usage of the defined node. Required variables should surround with <>, optional with []
     *
     * @return usage of the defined node
     */
    String getUsage();
    void setUsage(String usage);

    /**
     * Provides a short description of the defined node.
     *
     * @return description of the defined node
     */
    String getDescription();
    void setDescription(String description);


    /**
     * @return true if the node is optional, otherwise false
     */
    boolean isOptional();
    void setOptional(boolean optional);

    /**
     * Commands will generally return this as false.
     * This is expected to be used for variables that should be defined in the command as keys/flags or that act as a switch.
     * True Ex. `/fly -p [player] -s [speed double] -t [time int]` Order does not matter and no args have to be required.
     * False Ex. `/fly [player] [speed double] [time int]` Order matters and previous arg would be required.
     *
     * @return Optional.Empty if key should not be used, otherwise the {@link Optional<String>} key to use
     */
    Optional<String> usesKey();
    void setKey(String key);


}
