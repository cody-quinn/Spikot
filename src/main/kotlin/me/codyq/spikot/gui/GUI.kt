package me.codyq.spikot.gui

import me.codyq.spikot.dsl.item
import me.codyq.spikot.dsl.name
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

// TODO: Components, store logic for individual slot
// TODO: Views for handling logic of rendering things to a GUI

class GUI {
    var view: View = View()

    fun showTo(player: Player) {

    }

    fun handleClick(player: Player, itemStack: ItemStack, slot: Pair<Int, Int>) {
        val components = view.getComponents()
        components.firstOrNull { it.location == slot }?.onClick?.let { it(player, itemStack) }
    }
}

class View {
    private val components = mutableListOf<GUIComponent>()

    fun component(body: GUIComponent.() -> Unit) {
        val component = GUIComponent().apply(body)
        components.add(component)
    }

    fun getComponents() = components.toList()
}

class GUIComponent {
    var location: Pair<Int, Int> = Pair(0, 0)
    var item: ItemStack = ItemStack(Material.AIR)
    var onClick: (Player, ItemStack) -> Boolean = { player, itemStack -> false }
        private set

    fun onClick(body: (Player, ItemStack) -> Boolean) {
        onClick = body
    }
}

fun view(body: View.() -> Unit) = View().apply(body)