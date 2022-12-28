package org.pechblenda.pechblendaauth.service.`interface`

import org.springframework.http.ResponseEntity

import org.pechblenda.service.Request

import java.util.UUID

interface IRoleService {
	fun findAllRolesByUuid(roleUuid: UUID): ResponseEntity<Any>
}
