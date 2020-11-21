package com.exxbrain.profile

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterController {
    @PostMapping("register")
    fun register(user: User) {

    }
}