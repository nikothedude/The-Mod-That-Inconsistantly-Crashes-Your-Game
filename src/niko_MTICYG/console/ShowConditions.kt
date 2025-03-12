package niko_MTICYG.console

import niko_MTICYG.CombatScript
import niko_MTICYG.ConditionUpdater
import niko_MTICYG.effects.CrashCondition
import org.lazywizard.console.BaseCommand
import org.lazywizard.console.Console

class ShowConditions: BaseCommand {
    override fun runCommand(args: String, context: BaseCommand.CommandContext): BaseCommand.CommandResult {
        if (CrashCondition.activeConditons.isEmpty()) {
            Console.showMessage("No crash conditions are active. Rejoice.")
        } else {
            Console.showMessage(CrashCondition.activeConditons) // stringifies elegantly
        }

        return BaseCommand.CommandResult.SUCCESS
    }
}