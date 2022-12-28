package org.pechblenda.pechblendaauth.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping

import org.pechblenda.exception.HttpExceptionResponse
import org.pechblenda.service.Request
import org.pechblenda.doc.annotation.ApiDocumentation
import org.pechblenda.pechblendaauth.service.`interface`.IAuthService

import javax.servlet.http.HttpServletRequest

@CrossOrigin(methods = [
	RequestMethod.POST
])
@RestController
@RequestMapping(name = "Auth", value = ["/rest/auth"])
class AuthController(
	private val authService: IAuthService,
	private val httpExceptionResponse: HttpExceptionResponse
) {

	@PostMapping("/sign-up")
	@ApiDocumentation(path = "doc/auth/sign-up.json")
	fun signUp(
		@RequestBody request: Request,
		servletRequest: HttpServletRequest
	): ResponseEntity<Any> {
		return try {
			authService.signUp(request, servletRequest)
		} catch (e: ResponseStatusException) {
			httpExceptionResponse.error(e)
		}
	}

	@PostMapping("/sign-in")
	@ApiDocumentation(path = "doc/auth/sign-in.json")
	fun signIn(@RequestBody request: Request): ResponseEntity<Any> {
		return try {
			authService.signIn(request)
		} catch (e: ResponseStatusException) {
			httpExceptionResponse.error(e)
		}
	}

}
