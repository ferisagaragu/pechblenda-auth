package org.pechblenda.pechblendaauth.service.`interface`

import org.springframework.http.ResponseEntity

import org.pechblenda.service.Request

import javax.servlet.http.HttpServletRequest

interface IFingerprintService {
	fun singUpByFingerprint(request: Request, servletRequest: HttpServletRequest): ResponseEntity<Any>
	fun singInByFingerprint(request: Request): ResponseEntity<Any>
}
