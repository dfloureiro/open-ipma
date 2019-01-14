package com.dfl.openipma

import android.content.res.Resources

open class BaseUiModelMapper {

    internal val defaultUnknownDescription = Resources.getSystem().getString(android.R.string.unknownName)

    internal fun getIcon(id: Int): Int {
        return when (id) {
            1 -> R.drawable.ic_sunny                //Clear sky
            2 -> R.drawable.ic_partlycloudy         //Partly cloudy
            3 -> R.drawable.ic_partlycloudy         //Sunny intervals
            4 -> R.drawable.ic_cloudy               //Cloudy
            5 -> R.drawable.ic_cloudy               //Cloudy (High cloud)
            6 -> R.drawable.ic_pouring              //Showers
            7 -> R.drawable.ic_pouring              //Light showers
            8 -> R.drawable.ic_pouring              //Heavy showers
            9 -> R.drawable.ic_rainy                //Rain
            10 -> R.drawable.ic_rainy               //Light rain
            11 -> R.drawable.ic_rainy               //Heavy rain
            12 -> R.drawable.ic_rainy               //Intermittent rain
            13 -> R.drawable.ic_rainy               //Intermittent light rain
            14 -> R.drawable.ic_rainy               //Intermittent heavy rain
            15 -> R.drawable.ic_pouring             //Drizzle
            16 -> R.drawable.ic_fog                 //Mist
            17 -> R.drawable.ic_fog                 //Fog
            18 -> R.drawable.ic_snowy               //Snow
            19 -> R.drawable.ic_lightning           //Thunderstorms
            20 -> R.drawable.ic_lightning_rainy     //Showers and thunderstorms
            21 -> R.drawable.ic_hail                //Hail
            22 -> R.drawable.ic_frost               //Frost
            23 -> R.drawable.ic_lightning_rainy     //Rain and thunderstorms
            24 -> R.drawable.ic_cloudy              //Convective clouds
            25 -> R.drawable.ic_cloudy              //Partly cloudy
            26 -> R.drawable.ic_fog                 //Fog
            27 -> R.drawable.ic_cloudy              //Cloudy
            else -> R.drawable.ic_unknown_weather   //Unknown
        }
    }

    internal fun getBackgroundColor(id: Int): Int {
        return when (id) {
            1 -> R.color.sunny      //Clear sky
            2 -> R.color.cloudy     //Partly cloudy
            3 -> R.color.cloudy     //Sunny intervals
            4 -> R.color.cloudy     //Cloudy
            5 -> R.color.cloudy     //Cloudy (High cloud)
            6 -> R.color.rainy      //Showers
            7 -> R.color.rainy      //Light showers
            8 -> R.color.rainy      //Heavy showers
            9 -> R.color.rainy      //Rain
            10 -> R.color.rainy     //Light rain
            11 -> R.color.rainy     //Heavy rain
            12 -> R.color.rainy     //Intermittent rain
            13 -> R.color.rainy     //Intermittent light rain
            14 -> R.color.rainy     //Intermittent heavy rain
            15 -> R.color.rainy     //Drizzle
            16 -> R.color.cloudy    //Mist
            17 -> R.color.cloudy    //Fog
            18 -> R.color.snowy     //Snow
            19 -> R.color.rainy     //Thunderstorms
            20 -> R.color.rainy     //Showers and thunderstorms
            21 -> R.color.snowy     //Hail
            22 -> R.color.snowy     //Frost
            23 -> R.color.rainy     //Rain and thunderstorms
            24 -> R.color.cloudy    //Convective clouds
            25 -> R.color.cloudy    //Partly cloudy
            26 -> R.color.cloudy    //Fog
            27 -> R.color.cloudy    //Cloudy
            else -> R.color.cloudy  //Unknown
        }
    }
}