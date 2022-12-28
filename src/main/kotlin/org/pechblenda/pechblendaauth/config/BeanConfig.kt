package org.pechblenda.pechblendaauth.config

import org.pechblenda.doc.Documentation
import org.pechblenda.pechblendaauth.controller.AuthController
import org.pechblenda.pechblendaauth.controller.FingerprintController
import org.pechblenda.pechblendaauth.entity.User
import org.pechblenda.pechblendaauth.service.message.AuthMessage

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("org.pechblenda.bean")
class BeanConfig {

	@Bean
	fun documentation(): Documentation {
		return Documentation(
			mutableListOf(
				User::class
			),
			AuthController::class,
			FingerprintController::class
		)
	}

}