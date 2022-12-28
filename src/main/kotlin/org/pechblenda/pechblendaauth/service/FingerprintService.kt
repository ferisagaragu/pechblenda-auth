package org.pechblenda.pechblendaauth.service

import javax.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import org.pechblenda.service.Request
import org.pechblenda.service.Response
import org.pechblenda.pechblendaauth.service.`interface`.IFingerprintService

@Service
class FingerprintService(
	val response: Response
): IFingerprintService {

	@Transactional
	override fun singUpByFingerprint(request: Request, servletRequest: HttpServletRequest): ResponseEntity<Any> {
		return response.ok()
	}

	@Transactional
	override fun singInByFingerprint(request: Request): ResponseEntity<Any> {
		return response.ok()
	}

}
