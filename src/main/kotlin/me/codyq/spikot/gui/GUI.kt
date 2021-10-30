package me.codyq.spikot.gui

import me.codyq.spikot.dsl.item
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import kotlin.math.min

// TODO: Components, store logic for individual slot
// TODO: Views for handling logic of rendering things to a GUI
// TODO: Add support for paginated guis.


// val slot = (y * 9) + x
class GUI: InventoryHolder {
    var title: String = ""

    private val views = mutableListOf<View>()

    fun view(body: View.() -> Unit) {
        val view = View().apply(body)
        views.add(view)
    }

    internal fun handleClick(player: Player, itemStack: ItemStack, slotX: Int, slotY: Int) {
        views.forEach { view ->
            val components = view.getComponents()
            val firstComponent = components.firstOrNull { guiComponent ->  guiComponent.slotX == slotX && guiComponent.slotY == slotY }

            if (firstComponent != null) {
                firstComponent.onClick(player, itemStack)
                return@forEach
            }
        }
    }

    override fun getInventory(): Inventory {
        val inventoryHeight = views.maxByOrNull { it.height }?.height ?: 6
        val createInventory = Bukkit.createInventory(this, min(54, inventoryHeight * 9), title)

        var currentSlotX = 0

        for (view in views) {
            val height = view.height
            val width = view.width

            
        }

        return createInventory
    }
}

class View {
    private var _width: Int = 0
    private var _height: Int = 0

    var width: Int
        get() = _width
        set(value) { _width = min(9, value) }

    var height: Int
        get() = _height
        set(value) { _height = min(6, value) }

    private val components = mutableListOf<GUIComponent>()

    fun component(body: GUIComponent.() -> Unit) {
        val component = GUIComponent().apply(body)
        components.add(component)
    }

    fun getComponents() = components.toList()
}

// TODO: slots should be relative to the view.
class GUIComponent {
    var slotX: Int = 0
    var slotY: Int = 0

    var item: ItemStack = ItemStack(Material.AIR)

    var onClick: (Player, ItemStack) -> Boolean = { _, _ -> false }
        private set

    fun onClick(body: (Player, ItemStack) -> Boolean) {
        onClick = body
    }
}

fun gui(body: GUI.() -> Unit) = GUI().apply(body)
