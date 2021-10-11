package me.codyq.spikot.dsl

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

private fun item(itemStack: ItemStack, action: ItemStack.() -> Unit): ItemStack = itemStack.apply { action() }

fun item(material: Material = Material.AIR, amount: Int = 1, action: ItemStack.() -> Unit = { }): ItemStack =
    item(ItemStack(material, amount), action)

inline fun <reified T : ItemMeta> ItemStack.meta(action: T.() -> Unit): ItemStack =
    apply { itemMeta = this.itemMeta?.let { it as? T }?.apply(action) ?: this.itemMeta }

@JvmName("itemMeta")
fun ItemStack.meta(action: ItemMeta.() -> Unit) = meta<ItemMeta>(action)

var ItemStack.name: String
    get() = itemMeta?.displayName ?: ""
    set(value) { meta { setDisplayName(value) } }

var ItemStack.lore: List<String>
    get() = itemMeta?.lore ?: listOf()
    set(value) { meta { lore = value} }

operator fun ItemStack.invoke(action: ItemStack.() -> Unit) = item(this, action)
