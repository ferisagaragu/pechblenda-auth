package org.pechblenda.pechblendaauth.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

import org.pechblenda.service.Request
import org.pechblenda.service.Response
import org.pechblenda.pechblendaauth.service.`interface`.IAuthService
import org.pechblenda.auth.enums.AccountType
import org.pechblenda.exception.BadRequestException
import org.pechblenda.service.helper.Validation
import org.pechblenda.service.helper.ValidationType
import org.pechblenda.service.helper.Validations
import org.pechblenda.pechblendaauth.entity.User
import org.pechblenda.core.shared.DynamicResources
import org.pechblenda.pechblendaauth.security.IAuthRepository
import org.pechblenda.auth.entity.IUser
import org.pechblenda.exception.UnauthenticatedException
import org.pechblenda.security.JwtProvider
import org.pechblenda.pechblendaauth.service.message.AuthMessage

import javax.servlet.http.HttpServletRequest
import org.pechblenda.pechblendaauth.entity.Role
import org.pechblenda.pechblendaauth.repository.IRoleRepository

@Service
class AuthService(
	var authRepository: IAuthRepository,
	var passwordEncoder: PasswordEncoder,
	var dynamicResources: DynamicResources,
	var authenticationManager: AuthenticationManager,
	var jwtProvider: JwtProvider,
	var authMessage: AuthMessage,
	var roleRepository: IRoleRepository,
	val response: Response
): IAuthService {

	@Transactional
	override fun signUp(request: Request, servletRequest: HttpServletRequest): ResponseEntity<Any> {
		val user = request.to<User>(
			User::class,
			Validations(
				Validation(
					"name",
					authMessage.requiredName,
					ValidationType.NOT_NULL,
					ValidationType.NOT_BLANK
				),
				Validation(
					"surname",
					authMessage.requiredSurName,
					ValidationType.NOT_NULL,
					ValidationType.NOT_BLANK
				),
				Validation(
					"motherSurname",
					authMessage.requiredMotherSurName,
					ValidationType.NOT_NULL,
					ValidationType.NOT_BLANK
				),
				Validation(
					"userName",
					authMessage.requiredUserName,
					ValidationType.NOT_NULL,
					ValidationType.NOT_BLANK
				),
				Validation(
					"email",
					authMessage.requiredEmail,
					ValidationType.NOT_NULL,
					ValidationType.NOT_BLANK
				)
			)
		)

		if (authRepository.existsByUserName(user.userName)) {
			throw BadRequestException(authMessage.registeredUserName)
		}

		if (authRepository.existsByEmail(user.email)) {
			throw BadRequestException(authMessage.registeredEmail)
		}

		user.password = passwordEncoder.encode(user.password)
		user.enabled = true
		user.active = true
		user.photo = dynamicResources.getUserImageUrl(servletRequest, user)
		user.accountType = AccountType.DEFAULT.name

		val roles = mutableListOf<Role>()
		val editorRol = roleRepository.findRoleByName("editor").orElseThrow {
			BadRequestException("No se encuentra el rol")
		}

		authRepository.save(user)

		return response.created(authMessage.successSignUp)
	}

	@Transactional
	override fun signIn(request: Request): ResponseEntity<Any> {
		val user = request.to<IUser>(
			User::class,
			Validations(
				Validation(
					"userName",
					authMessage.requiredUserName,
					ValidationType.NOT_NULL,
					ValidationType.NOT_BLANK
				),
				Validation(
					"password",
					authMessage.requiredPassword,
					ValidationType.NOT_NULL,
					ValidationType.NOT_BLANK
				)
			)
		)
		val userOut = authRepository.findByUserNameOrEmail(
			user.userName
		).orElseThrow { throw BadRequestException(authMessage.errorUserNotFount, "userName") }

		if (!userOut.active) {
			throw BadRequestException(authMessage.errorAccountNotActive)
		}

		if (!userOut.enabled) {
			throw BadRequestException(authMessage.errorAccountBlocked)
		}

		val session: Map<String, Any>

		try {
			val authentication = authenticationManager.authenticate(
				UsernamePasswordAuthenticationToken(
					userOut.userName,
					user.password
				)
			)

			session = jwtProvider.generateJwtTokenRefresh(authentication)
		} catch (e: Exception) {
			println(e.message)
			throw UnauthenticatedException("El usuario o contraseña que ingresaste son icorrectos", "token")
		}

		val out = response.toMap(
			userOut
		)

		out["session"] = session

		return out
			.exclude(
				"password",
				"enabled",
				"active",
				"activatePassword"
			)
			.firstId()
			.ok()
	}

}
