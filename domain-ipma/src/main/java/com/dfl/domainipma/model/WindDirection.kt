package com.dfl.domainipma.model

enum class WindDirection(val direction: String, val rotation: Float) {
    N("NORTH", 0f),
    NE("NORTHEAST", 45f),
    E("EAST", 90f),
    SE("SOUTHEAST", 135f),
    S("SOUTH", 180f),
    SW("SOUTHWEST", 225f),
    W("WEST", 270f),
    NW("NORTHWEST", 315f)
}
