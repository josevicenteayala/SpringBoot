package com.application.boot.contactos.component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.application.boot.contactos.entity.LogEntity;
import com.application.boot.contactos.repository.LogRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestTimeInterceptor.
 */
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter{

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestTimeInterceptor.class);  
	
	/** The Constant INICIO_LLAMADO. */
	private static final String INICIO_LLAMADO = "inicioLlamado";
	
	/** The log repository. */
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute(INICIO_LLAMADO, Instant.now());
		return super.preHandle(request, response, handler);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Instant momentoInicial = (Instant)request.getAttribute(INICIO_LLAMADO);
		Instant momentoActual = Instant.now();
		long diferenciaEnMilisegundos = ChronoUnit.MILLIS.between(momentoInicial, momentoActual);
		String url = request.getRequestURL().toString();
		LogEntity logEntity = new LogEntity();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		String details = "";
		if(Objects.nonNull(auth) && auth.isAuthenticated() && Objects.nonNull(auth.getDetails())) {
			username = auth.getName();
			details = auth.getDetails().toString();
		}
		
		logEntity.setDate(new Date());
		logEntity.setDetails(details);
		logEntity.setUrl(url);
		logEntity.setUsername(username);
		logRepository.save(logEntity);
		StringBuilder sbLog = new StringBuilder();
		sbLog.append("REQUEST URL: ");
		sbLog.append(url);
		sbLog.append(" TIEMPO TOTAL EN MILISEGUNDOS PARA SER LLAMADO: ");
		sbLog.append(diferenciaEnMilisegundos);
		sbLog.append(" -- El Usuario autenticado es ");
		sbLog.append(username);
		sbLog.append("  -- el detalle es ");
		sbLog.append(details);
		LOGGER.info(sbLog.toString());
		super.afterCompletion(request, response, handler, ex);
	}
	
}
