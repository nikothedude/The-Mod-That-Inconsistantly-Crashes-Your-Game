package niko_MTICYG.effects

import com.fs.starfarer.api.util.WeightedRandomPicker
import niko_MTICYG.ConditionUpdater
import niko_MTICYG.MTICYG_modPlugin
import java.util.concurrent.locks.Condition

enum class CrashCondition(
    val pickWeight: Float,
    val cost: Float
) {
    // META
    ON_START(10f, 20f) { // just instantly crashes the game lol
        override fun apply() {
            MTICYG_modPlugin.throwRandomException()
        }

        override fun unapply() {
            MTICYG_modPlugin.throwRandomException()
        }
    },
    ON_LOAD(10f, 20f),
    // CAMPAIGN
    ON_SAVE(200f, 60f),
    MARKET_INTERACTION(500f, 40f),
    ECONOMY_TICK(200f, 40f),
    REP_CHANGE(600f, 30f),
    PLAYER_FLEET_JUMPS(700f, 30f),
    INTERACTION_DIALOG_SHOWN(300f, 70f),
    ABILITY_USED(500f, 30f),
    MARKET_DECIVVED(600f, 20f),
    ENTITY_DISCOVERED(600f, 50f),
    GATE_TRANSITTED(600f, 20f),
    PLANET_SURVEYED(500f, 30f),
    // COMBAT
    SIMULATOR_STARTED(300f, 20f),
    BATTLE_STARTED(100f, 70f);

    fun picked() {

    }

    open fun apply() { }
    open fun unapply() { }

    companion object {
        const val BASE_BUDGET = 100f

        val activeConditons: MutableSet<CrashCondition> = HashSet()

        fun getNewEffects(budget: Float) {
            activeConditons.forEach { removeEffect(it) }
            activeConditons.clear()

            val effects = getEffects(budget)
            effects.forEach { addNewEffect(it) }

            ConditionUpdater.get()?.reported = false
        }

        private fun addNewEffect(condition: CrashCondition) {
            activeConditons += condition
            condition.apply()
        }

        private fun removeEffect(condition: CrashCondition) {
            activeConditons -= condition
            condition.unapply()
        }

        fun getEffects(budget: Float): MutableSet<CrashCondition> {
            var budget = budget

            val effects: MutableSet<CrashCondition> = HashSet()
            val picker = WeightedRandomPicker<CrashCondition>()

            for (entry in CrashCondition.values()) {
                picker.add(entry, entry.pickWeight)
            }

            while (budget > 0 && !picker.isEmpty) {
                val picked = picker.pickAndRemove()
                if (picked.cost > budget) {
                    continue
                }
                budget -= picked.cost
                picked.picked()
                effects += picked
            }

            return effects
        }

        fun effectActive(id: CrashCondition): Boolean {
            return activeConditons.any { it == id }
        }

        fun getChangedString(): String {
            return "NEW CONDITIONS! $activeConditons"
        }
    }
}