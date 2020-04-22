package com.hellodiffa.googlemaps_recylerview_interaction.utils

import com.hellodiffa.googlemaps_recylerview_interaction.model.Hotel
/*
* CREATED By Diffa
* linkedin : www.linkedin.com/in/diffadwi
* github : www.github.com/ddiffa
* email : ddiffa2@gmail.com
*/

fun getData(): List<Hotel> {
    return arrayListOf(
        Hotel(
            1,
            "Hotel mawar indah ",
            3_000_000,
            "https://www.rwsentosa.com/-/media/project/non-gaming/rwsentosa/hotels/hard-rock-hotel-singapore/hardrockhotelsg-exterior.jpg",
            -7.796248,
            110.374710
        ),
        Hotel(
            2,
            "Hotel mawar merah ",
            4_000_000,
            "https://pix10.agoda.net/hotelImages/930/930782/930782_16010416400038860307.jpg?s=1024x768",
            -7.799248,
            110.584710
        ),
        Hotel(
            3,
            "Hotel latulip ",
            3_500_000,
            "https://pix10.agoda.net/hotelImages/186/186438/186438_17020216190050733581.jpg",
            -7.806248,
            110.844710
        ),
        Hotel(
            4,
            "Hotel matahari ",
            2_600_000,
            "https://q-xx.bstatic.com/xdata/images/hotel/840x460/190621974.jpg?k=1d6fb8336ce2f6e0a3abdb453804fb05b80f547674457c4b37e5447524254b8c&o=",
            -7.826248,
            111.364710
        ),
        Hotel(
            5,
            "Hotel sakura ",
            7_000_000,
            "https://i0.wp.com/f1-styx.imgix.net/article/2019/09/08092345/4-3.jpg?fit=1309%2C736&ssl=1",
            -7.846248,
            110.324710
        )
    )
}