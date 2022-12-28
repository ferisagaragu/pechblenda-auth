package org.pechblenda.pechblendaauth.service.`interface`

import javax.servlet.http.HttpServletRequest

import org.springframework.http.ResponseEntity

import org.pechblenda.service.Request

interface IAuthService {
	fun signUp(request: Request, servletRequest: HttpServletRequest): ResponseEntity<Any>
	fun signIn(request: Request): ResponseEntity<Any>
}
