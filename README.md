# ModuleMonitor
Android application that that uses protocol buffers to take in remote battery values and displays current and historical values for voltage, usage and charging history.

[Link to current APK ](https://github.com/ahrenswett/ModuleMonitor/blob/main/ModMon/release/ModMon-release.apk)

[MockPlus mockup of app](https://app.mockplus.com/run/rp/rRPKyTS8fnoYg/cGytQlHAEDWt-?ps=1&ha=0&la=0&fc=0&dt=android&out=1)

## onStart and onResume
- ModMon will load any exsting modules and fetch the most current data for the packs

<img src="https://github.com/ahrenswett/ModuleMonitor/blob/1e0b667bb8f7bd3b596ef94c1c6b89cb146e4eed/markdownFiles/Screen%20Shot%202021-03-04%20at%202.46.14%20PM.png" alt="onStart Screen" width="200"/>

- Press the + button and it will request information for a new pack.

<img src="https://github.com/ahrenswett/ModuleMonitor/blob/1e0b667bb8f7bd3b596ef94c1c6b89cb146e4eed/markdownFiles/Screen%20Shot%202021-03-04%20at%202.46.40%20PM.png" alt alt="add a pack image" width="200"/>

- A new pack will then display in the recycler view
<img src="https://github.com/ahrenswett/ModuleMonitor/blob/1e0b667bb8f7bd3b596ef94c1c6b89cb146e4eed/markdownFiles/Screen%20Shot%202021-03-04%20at%202.49.59%20PM.png" alt="2 packs on home screen" width="200"/>

** Implement OTA updates to the arduino for feature 
