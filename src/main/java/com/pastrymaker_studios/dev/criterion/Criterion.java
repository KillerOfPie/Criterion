/*
 * # Pie License Version 1.0 Summary
 *
 * The Pie License, provided by KillerOfPie & PastryMaker Studios, is a modified MIT/X11 License offering freedom for using, modifying, and distributing the software. Key terms include:
 *
 * 1. Include the copyright notice, permission notice, and contributors file in all copies.
 * 2. Distribute non-minified, non-obfuscated versions with the original, human-readable license intact and attribute to the original authors and contributors.
 * 3. Avoid licensing conflicts, prioritizing this license and the Open Source Definition.
 * 4. Distribute substantial modifications with a modified name or versioning scheme referencing the original version number.
 * 5. Include comprehensive details of individuals or non-person entities involved in substantial modifications in the contributors file.
 *
 * The software is provided "as is" without warranty. The copyright holders and contributors are not liable for any claim, damages, or liability. Use of names in advertising requires prior written authorization, except as specified in the contributors file.
 *
 * This project should contain a copy of the Pie License. If it does not and have not received a copy of it along with this program, see ${license.url} for a copy of the license and contact ${license.holders} the issue can be corrected.
 */
package com.pastrymaker_studios.dev.criterion;

import com.bergerkiller.bukkit.common.PluginBase;
import com.pastrymaker_studios.dev.criterion.module.modulemanagement.Manager;
import lombok.Getter;
import org.bukkit.command.CommandSender;

public class Criterion extends PluginBase {

    private static Criterion instance = null;

    public Criterion() {
        if (instance != null) {
            throw new UnsupportedOperationException("Attempting to create further instance of singleton class " + this.getClass() + "!");
        }
    }

    public static Criterion getInstance() {
        if(instance == null) {
            instance = new Criterion();
        }
        return instance;
    }

    /**
     * Gets the minimum BKCommonLib version required for this Plugin to
     * function<br>
     * Override this and return Common.VERSION as result (compiler will
     * automatically inline this)
     *
     * @return Minimum BKCommonLib version number
     */
    @Override
    public int getMinimumLibVersion() {
        return 0;
    }

    /**
     * Called when this plugin is being enabled
     */
    @Override
    public void enable() {
    }

    /**
     * Called when this plugin is being disabled
     */
    @Override
    public void disable() {

    }

    /**
     * Handles a command
     *
     * @param sender  of the command
     * @param command name
     * @param args    of the command
     * @return True if handled, False if not
     */
    @Override
    public boolean command(CommandSender sender, String command, String[] args) {
        return false;
    }
}
