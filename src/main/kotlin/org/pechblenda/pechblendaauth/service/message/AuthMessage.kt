package org.pechblenda.pechblendaauth.service.message

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:message/auth-message.properties")
class AuthMessage {
	@Value("\${auth.message.required.name}") lateinit var requiredName: String
	@Value("\${auth.message.required.surname}") lateinit var requiredSurName: String
	@Value("\${auth.message.required.mother-surname}") lateinit var requiredMotherSurName: String
	@Value("\${auth.message.required.user-name}") lateinit var requiredUserName: String
	@Value("\${auth.message.required.email}") lateinit var requiredEmail: String
	@Value("\${auth.message.required.password}") lateinit var requiredPassword: String

	@Value("\${auth.message.registered.user-name}") lateinit var registeredUserName: String
	@Value("\${auth.message.registered.email}") lateinit var registeredEmail: String

	@Value("\${auth.message.success.sign-up}") lateinit var successSignUp: String

	@Value("\${auth.message.error.user-not-fount}") lateinit var errorUserNotFount: String
	@Value("\${auth.message.error.account-not-active}") lateinit var errorAccountNotActive: String
	@Value("\${auth.message.error.account-blocked}") lateinit var errorAccountBlocked: String
}