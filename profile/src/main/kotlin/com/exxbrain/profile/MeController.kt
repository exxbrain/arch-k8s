package com.exxbrain.profile

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("me")
class MeController {
    @GetMapping("/")
    fun me(): User {
        return User("test")
    }

    @PatchMapping("/")
    fun update() {
    }
}