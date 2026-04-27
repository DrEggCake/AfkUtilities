# AfkUtilities

## Description
A small AFK utility plugin which adds missing AFK features for other plugins.

---

## Features
- Automatically teleports players to a configurable location after X seconds of AFK (in config.yml)
- Per-group AFK time override using LuckPerms
- Prevents teleporting players inside configured AFK regions (WorldGuard support)

---

## Requirements
- Minecraft version: 1.21 - 1.21.5+
- Server software: Paper / Spigot
- Java version: 21+

---

## Installation
1. Download the plugin `.jar`
2. Place it into your `/plugins` folder
3. Restart the server
4. Configure files in `/plugins/AfkUtilities/`

---

## Commands

### /afkutilities
Main admin command

| Subcommand     | Description                              | Permission              |
|----------------|------------------------------------------|--------------------------|
| (none)         | Shows help message                       | afkutilities.admin       |
| reload         | Reloads plugin configuration             | afkutilities.admin       |
| setlocation    | Sets AFK teleport location               | afkutilities.admin       |
| setregion      | Sets AFK protection region (WorldGuard)  | afkutilities.admin       |

---

### /afk or /afkarea
AFK teleport command

| Usage           | Description                              | Permission              |
|----------------|------------------------------------------|--------------------------|
| /afk           | Teleports yourself to AFK area           |                          |
| /afk <player>  | Teleports another player to AFK area     | afkutilities.player      |

---

## Permissions

| Permission              | Description                          | Default |
|------------------------|--------------------------------------|---------|
| afkutilities.admin     | Access to all admin commands         | op      |
| afkutilities.player    | Allows AFK teleport command usage    | op      |

---

## Configuration
- config.yml controls teleport coordinates, duration before teleporting, and the world guard region
- WorldGuard region name can be configured
- AFK location is set via `/afkutilities setlocation` or in config.yml

---

## Dependencies
- WorldGuard (for region protection from being teleported in afk region)
- LuckPerms (optional, for group-based AFK time overrides)

---

## Build Instructions (for developers)

```bash
./gradlew build
```

---
## Credits

This plugin uses the following libraries for development:

- Foundation™ by MineAcademy: https://github.com/kangarko/Foundation
- ACF: https://github.com/aikar/commands

