package co.com.intergrupo.maven.boot.component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestTimeInterceptor.class);

	
	/* Metodo que se ejecuta de manera inicial
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("momentoInicial", Instant.now());
		return true;
	}	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Instant momentoInicial = (Instant)request.getAttribute("momentoInicial");
		Instant momentoActual = Instant.now();
		long diferenciaEnMilisegundos = ChronoUnit.MILLIS.between(momentoInicial, momentoActual);
		LOGGER.info("REQUEST URL: "+request.getRequestURL().toString()+" TIEMPO TOTAL EN MILISEGUNDOS PARA SER LLAMADO: "+diferenciaEnMilisegundos);
	}


}
