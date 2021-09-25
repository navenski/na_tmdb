package com.navektest.business.model

/**
 * @param path the path of the picture
 * @param lowDefUrl the resolved low definition url
 * @param midDefUrl the resolved middle definition url
 * @param highDefUrl the resolved high definition url
 */
data class Picture(val path: String, val lowDefUrl: String, val midDefUrl: String, val highDefUrl: String){
    val hasPicture get() =  path.isNotEmpty()
}
