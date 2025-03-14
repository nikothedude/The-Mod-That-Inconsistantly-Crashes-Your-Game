package niko_MTICYG

import lunalib.lunaSettings.LunaSettings

object Settings {
    var baseBudget = 100f
    var updateConditions = true
    var reportConditionChanges = false

    var canAddConditionUpdaterYet = false

    var lunaLibEnabled = false
    const val modId = "niko_MTICYG"

    fun reloadSettings() {
        if (!lunaLibEnabled) return

        baseBudget = LunaSettings.getFloat(modId, "MTICYG_baseBudget")!!
        updateConditions = LunaSettings.getBoolean(modId, "MTICYG_updateConditions")!!
        reportConditionChanges = LunaSettings.getBoolean(modId, "MTICYG_reportConditions")!!
    }
}