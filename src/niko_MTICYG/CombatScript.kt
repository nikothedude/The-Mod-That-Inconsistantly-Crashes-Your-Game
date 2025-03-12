package niko_MTICYG

import com.fs.starfarer.api.GameState
import com.fs.starfarer.api.Global
import com.fs.starfarer.api.combat.BaseEveryFrameCombatPlugin
import com.fs.starfarer.api.combat.CombatEngineAPI
import com.fs.starfarer.api.input.InputEventAPI
import niko_MTICYG.MTICYG_modPlugin.Companion.throwRandomException
import niko_MTICYG.effects.CrashCondition

class CombatScript: BaseEveryFrameCombatPlugin() {

    override fun init(engine: CombatEngineAPI) {
        super.init(engine)

        if (Global.getCurrentState() == GameState.TITLE) return

        if (engine.isSimulation) {
            if (CrashCondition.effectActive(CrashCondition.SIMULATOR_STARTED)) {
                throwRandomException()
            }
        } else {
            if (CrashCondition.effectActive(CrashCondition.BATTLE_STARTED)) {
                throwRandomException()
            }
        }
    }

    override fun advance(amount: Float, events: MutableList<InputEventAPI>?) {
        super.advance(amount, events)

        ConditionUpdater.get()?.advance(amount)
    }
}