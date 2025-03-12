package niko_MTICYG

import com.fs.starfarer.api.BaseModPlugin
import com.fs.starfarer.api.Global
import com.fs.starfarer.api.util.WeightedRandomPicker
import lunalib.lunaSettings.LunaSettings
import niko_MTICYG.effects.CrashCondition
import java.lang.Exception
import java.lang.NumberFormatException
import java.lang.RuntimeException
import kotlin.NullPointerException

class MTICYG_modPlugin: BaseModPlugin() {

    companion object {
        val weightedExceptionList = hashMapOf(
            Pair(RuntimeException::class.java, 5f),
            Pair(NullPointerException::class.java, 5f),
            Pair(ConcurrentModificationException::class.java, 5f),
            Pair(SecurityException::class.java, 5f),
            Pair(NumberFormatException::class.java, 1f)
        )

        fun throwRandomException(): Class<out Exception> {
            val picker = WeightedRandomPicker<Class<out Exception>>()
            weightedExceptionList.forEach { picker.add(it.key, it.value) }
            val random = picker.pick() ?: NullPointerException::class.java

            throw random.newInstance()
        }
    }

    override fun onApplicationLoad() {
        super.onApplicationLoad()

        CrashCondition.getNewEffects(Settings.baseBudget)

        Settings.lunaLibEnabled = Global.getSettings().modManager.isModEnabled("lunalib")
        Settings.reloadSettings()
    }

    override fun onGameLoad(newGame: Boolean) {
        super.onGameLoad(newGame)

        if (CrashCondition.effectActive(CrashCondition.ON_LOAD)) {
            throwRandomException()
            return
        }

        Global.getSector().addTransientListener(UltimateListener())
        Global.getSector().listenerManager.addListener(UltimateListener(), true)

        Settings.canAddConditionUpdaterYet = true
        ConditionUpdater.get()

        if (Settings.lunaLibEnabled) {
            LunaSettings.addSettingsListener(settingsChangedListener())
            LunaSettings.addSettingsListener(settingsChangedListener())
        }
    }

    override fun afterGameSave() {
        super.afterGameSave()

        if (CrashCondition.effectActive(CrashCondition.ON_SAVE)) {
            throwRandomException()
        }
    }

}