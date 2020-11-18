package com.exxbrain.profile

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {
    @GetMapping("me")
    fun me(): User {
        return User("test")
    }
}