package niko_MTICYG

import com.fs.starfarer.api.EveryFrameScript
import com.fs.starfarer.api.Global
import com.fs.starfarer.api.util.IntervalUtil
import niko_MTICYG.effects.CrashCondition

class ConditionUpdater: EveryFrameScript {

    companion object {
        fun get(): ConditionUpdater? {
            if (!Settings.updateConditions) return null
            val sector = Global.getSector() ?: return null

            var script: ConditionUpdater? = sector.transientScripts.firstOrNull { it::class.java == ConditionUpdater::class.java } as? ConditionUpdater
            if (script == null) {
                script = ConditionUpdater()
                sector.addTransientScript(script)
            }
            return script
        }

        const val MIN_SECONDS_BETWEEN_REFRESHSES = (30f * 60f) // 30 mins
        const val MAX_SECONDS_BETWEEN_REFRESHSES = (40f * 60f) // 40 mins
    }

    val interval = IntervalUtil(MIN_SECONDS_BETWEEN_REFRESHSES, MAX_SECONDS_BETWEEN_REFRESHSES) // seconds

    override fun isDone(): Boolean = false
    override fun runWhilePaused(): Boolean = true

    override fun advance(amount: Float) {
        interval.advance(amount)
        if (interval.intervalElapsed()) {
            CrashCondition.getNewEffects(Settings.baseBudget)
        }
    }
}