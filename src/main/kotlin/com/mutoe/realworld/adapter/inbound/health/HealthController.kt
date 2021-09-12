package com.mutoe.realworld.adapter.inbound.health

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @RequestMapping("/health")
    fun healthCheck(): String {
        return "Hello!"
    }
}
