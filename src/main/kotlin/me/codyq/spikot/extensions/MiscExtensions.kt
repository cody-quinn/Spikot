package me.codyq.spikot.extensions

import org.bukkit.Bukkit

val server get() = Bukkit.getServer()
val pluginManager get() = Bukkit.getPluginManager()
val worlds get() = Bukkit.getWorlds()
