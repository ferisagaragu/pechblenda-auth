package org.pechblenda.pechblendaauth.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import org.pechblenda.service.Request
import org.pechblenda.service.Response
import org.pechblenda.pechblendaauth.repository.IRoleRepository
import org.pechblenda.pechblendaauth.service.`interface`.IRoleService

import java.util.UUID

@Service
class RoleService(
	val roleRepository: IRoleRepository,
	val response: Response
): IRoleService {

	@Transactional(readOnly = true)
	override fun findAllRolesByUuid(roleUuid: UUID): ResponseEntity<Any> {
		TODO("Not yet implemented")
	}

}
