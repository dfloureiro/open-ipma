package com.dfl.domainipma.model

enum class WindDirection(val direction: String, val rotation: Float) {
    S("SOUTH", 0f),
    SW("SOUTHWEST", 45f),
    W("WEST", 90f),
    NW("NORTHWEST", 135f),
    N("NORTH", 180f),
    NE("NORTHEAST", 225f),
    E("EAST", 270f),
    SE("SOUTHEAST", 315f)
}