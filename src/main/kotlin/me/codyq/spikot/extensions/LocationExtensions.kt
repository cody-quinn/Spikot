package me.codyq.spikot.extensions

import org.bukkit.Location
import org.bukkit.util.Vector
import kotlin.math.abs

fun List<Location>.asBlocks() = map { it.block }
fun Location.blockLocation() = Location(world, blockX.toDouble(), blockY.toDouble(), blockZ.toDouble())
fun List<Location>.blockLocations() = map { it.blockLocation() }

operator fun Location.component1() = x
operator fun Location.component2() = y
operator fun Location.component3() = z
operator fun Location.component4() = pitch
operator fun Location.component5() = yaw

operator fun Location.plus(b: Location): Location = add(b)
operator fun Location.minus(b: Location): Location = subtract(b)
operator fun Location.plus(b: Vector): Location = add(b)
operator fun Location.minus(b: Vector): Location = subtract(b)

operator fun Vector.plus(b: Vector): Vector = add(b)
operator fun Vector.minus(b: Vector): Vector = subtract(b)
operator fun Vector.plus(b: Location): Vector = add(b.toVector())
operator fun Vector.minus(b: Location): Vector = subtract(b.toVector())

operator fun Location.unaryMinus() = Location(world, -x, -y, -z)
operator fun Location.unaryPlus() = Location(world, abs(x), abs(y), abs(z))
