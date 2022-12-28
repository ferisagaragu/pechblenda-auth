package org.pechblenda.pechblendaauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class PechblendaAuthApplication

fun main(args: Array<String>) {
	runApplication<PechblendaAuthApplication>(*args)
}
