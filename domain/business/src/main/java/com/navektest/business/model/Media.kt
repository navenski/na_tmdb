package com.navektest.business.model

data class Media(val id: Int,
                 val title: String,
                 val type: MediaType,
                 val poster: Picture,
                 val backdrop: Picture,
                 val profile: Picture) {

    val hasPicture
        get() = if (type.isPerson) {
            profile.hasPicture
        } else {
            poster.hasPicture
        }

    val defaultPicture get() = if (type.isPerson) profile else poster
}
