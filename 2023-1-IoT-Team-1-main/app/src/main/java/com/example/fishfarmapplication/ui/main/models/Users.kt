package com.example.fishfarmapplication.ui.main.models

class User {
    var id: String = ""
    var password: String = ""

    constructor()

    constructor(id: String, password: String) {
        this.id = id
        this.password = password
    }
}