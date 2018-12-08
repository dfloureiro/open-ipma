package com.dfl.openipma

class WeatherTypeIconMapper {

    fun getIcon(id: Int): Int {
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
            27 -> R.drawable.ic_cloudy              //Cloudyk
            else -> R.drawable.ic_unknown_weather   //Unknown
        }
    }
}