package com.ivantha.playtolearn.model

import java.io.Serializable

class SaveFile : Serializable {
    var profile: Profile? = Profile()
    var board: Board? = Board()
}
