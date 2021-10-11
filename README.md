<h2 align="center">
    Spikot is a Kotlin library to make Spigot development easier 
</h2>

### Using Spikot

##### Installation

To use spikot add the following to your `build.gradle.kts` file. This will install the latest version

```kt
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.CatDevz:Spikot:-SNAPSHOT")
}
```

##### Examples

Item builder DSL:

```kt
val player = Bukkit.getOnlinePlayers().random()
    
// Creating the ItemStack
val itemStack = item(Material.PLAYER_HEAD) { 
    name = "${player.name}'s Skull"
    lore = listOf("The skull of ${player.name}")
    
    meta<SkullMeta> { 
        owningPlayer = player
    }
}
```
