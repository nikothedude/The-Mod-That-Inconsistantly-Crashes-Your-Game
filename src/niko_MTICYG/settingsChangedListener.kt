package niko_MTICYG

import lunalib.lunaSettings.LunaSettingsListener

class settingsChangedListener: LunaSettingsListener {
    override fun settingsChanged(modID: String) {
        Settings.reloadSettings()
    }
}