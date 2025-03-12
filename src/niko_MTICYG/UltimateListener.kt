package niko_MTICYG

import com.fs.starfarer.api.Global
import com.fs.starfarer.api.campaign.*
import com.fs.starfarer.api.campaign.econ.MarketAPI
import com.fs.starfarer.api.campaign.listeners.ColonyDecivListener
import com.fs.starfarer.api.campaign.listeners.GateTransitListener
import com.fs.starfarer.api.campaign.listeners.SurveyPlanetListener
import com.fs.starfarer.api.characters.AbilityPlugin
import com.fs.starfarer.api.characters.PersonAPI
import com.fs.starfarer.api.combat.EngagementResultAPI
import niko_MTICYG.MTICYG_modPlugin.Companion.throwRandomException
import niko_MTICYG.effects.CrashCondition

class UltimateListener: CampaignEventListener, ColonyDecivListener, GateTransitListener, SurveyPlanetListener {

    override fun reportPlayerOpenedMarket(market: MarketAPI?) {
        if (CrashCondition.effectActive(CrashCondition.MARKET_INTERACTION)) {
            throwRandomException()
        }
    }

    override fun reportPlayerClosedMarket(market: MarketAPI?) {
        if (CrashCondition.effectActive(CrashCondition.MARKET_INTERACTION)) {
            throwRandomException()
        }
    }

    override fun reportPlayerOpenedMarketAndCargoUpdated(market: MarketAPI?) {
        if (CrashCondition.effectActive(CrashCondition.MARKET_INTERACTION)) {
            throwRandomException()
        }
    }

    override fun reportEncounterLootGenerated(plugin: FleetEncounterContextPlugin?, loot: CargoAPI?) {
        return
    }

    override fun reportPlayerMarketTransaction(transaction: PlayerMarketTransaction?) {
        return
    }

    override fun reportBattleOccurred(primaryWinner: CampaignFleetAPI?, battle: BattleAPI?) {
        return
    }

    override fun reportBattleFinished(primaryWinner: CampaignFleetAPI?, battle: BattleAPI?) {
        return
    }

    override fun reportPlayerEngagement(result: EngagementResultAPI?) {
        return
    }

    override fun reportFleetDespawned(
        fleet: CampaignFleetAPI?,
        reason: CampaignEventListener.FleetDespawnReason?,
        param: Any?
    ) {
        return
    }

    override fun reportFleetSpawned(fleet: CampaignFleetAPI?) {
        return
    }

    override fun reportFleetReachedEntity(fleet: CampaignFleetAPI?, entity: SectorEntityToken?) {
        return
    }

    override fun reportFleetJumped(
        fleet: CampaignFleetAPI?,
        from: SectorEntityToken?,
        to: JumpPointAPI.JumpDestination?
    ) {
        if (fleet == Global.getSector().playerFleet) {
            if (CrashCondition.effectActive(CrashCondition.PLAYER_FLEET_JUMPS)) {
                throwRandomException()
            }
        }
    }

    override fun reportShownInteractionDialog(dialog: InteractionDialogAPI?) {
        if (CrashCondition.effectActive(CrashCondition.INTERACTION_DIALOG_SHOWN)) {
            throwRandomException()
        }
    }

    override fun reportPlayerReputationChange(faction: String?, delta: Float) {
        if (CrashCondition.effectActive(CrashCondition.REP_CHANGE)) {
            throwRandomException()
        }
    }

    override fun reportPlayerReputationChange(person: PersonAPI?, delta: Float) {
        if (CrashCondition.effectActive(CrashCondition.REP_CHANGE)) {
            throwRandomException()
        }
    }

    override fun reportPlayerActivatedAbility(ability: AbilityPlugin?, param: Any?) {
        if (CrashCondition.effectActive(CrashCondition.ABILITY_USED)) {
            throwRandomException()
        }
    }

    override fun reportPlayerDeactivatedAbility(ability: AbilityPlugin?, param: Any?) {
        if (CrashCondition.effectActive(CrashCondition.ABILITY_USED)) {
            throwRandomException()
        }
    }

    override fun reportPlayerDumpedCargo(cargo: CargoAPI?) {
        return
    }

    override fun reportPlayerDidNotTakeCargo(cargo: CargoAPI?) {
        return
    }

    override fun reportEconomyTick(iterIndex: Int) {
        if (CrashCondition.effectActive(CrashCondition.ECONOMY_TICK)) {
            throwRandomException()
        }
    }

    override fun reportEconomyMonthEnd() {
        return
    }

    override fun reportColonyAboutToBeDecivilized(market: MarketAPI?, fullyDestroyed: Boolean) {
        return
    }

    override fun reportColonyDecivilized(market: MarketAPI?, fullyDestroyed: Boolean) {
        if (CrashCondition.effectActive(CrashCondition.MARKET_DECIVVED)) {
            throwRandomException()
        }
    }

    override fun reportFleetTransitingGate(
        fleet: CampaignFleetAPI?,
        gateFrom: SectorEntityToken?,
        gateTo: SectorEntityToken?
    ) {
        if (fleet == Global.getSector().playerFleet && CrashCondition.effectActive(CrashCondition.GATE_TRANSITTED)) {
            throwRandomException()
        }
    }

    override fun reportPlayerSurveyedPlanet(planet: PlanetAPI?) {
        if (CrashCondition.effectActive(CrashCondition.PLANET_SURVEYED)) {
            throwRandomException()
        }
    }
}