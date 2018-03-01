package co.com.intergrupo.maven.boot.controller.curso;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.com.intergrupo.maven.boot.dto.CursoDto;
import co.com.intergrupo.maven.boot.dto.CursoResponse;
import co.com.intergrupo.maven.boot.service.CursoService;

@Controller
@RequestMapping("/cursos")
public class CursoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);
	
	public static final String VISTA_CURSOS = "cursos";
	@Autowired
	@Qualifier("cursoServiceImpl")
	private CursoService cursoService;
	
	@RequestMapping(value="/consultaListaDeCursosPorPrecio",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CursoResponse listaCursosPorPrecio(@RequestParam(name="precio",required=true) int precio) {		
		List<CursoDto> listaCursos = cursoService.listaCursosPorPrecio(precio);
		Objects.requireNonNull(listaCursos);
		listaCursos.stream().forEach(curso->System.out.println(curso));
		CursoResponse cursoResponse = new CursoResponse();
		cursoResponse.setListaCursos(listaCursos);
		return cursoResponse;
	}
	
	@GetMapping("/listaCompletaCursos")
	@ResponseBody
	public CursoResponse listaTodosLosCursos(){
		CursoResponse cursoResponse = new CursoResponse();
		List<CursoDto> listaTodosLosCursos = cursoService.listaTodosLosCursos();
		cursoResponse.setListaCursos(listaTodosLosCursos);
		return cursoResponse;
	}
	
	@PostMapping(value="/crearCurso", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CursoResponse crearCurso(@RequestBody(required=true) CursoDto cursoDto) {
		CursoResponse cursoResponse = new CursoResponse();		
		CursoDto cursoDtoCreado = cursoService.crearCurso(cursoDto);
		cursoResponse.setCursoCreadoActualizado(cursoDtoCreado);
		return cursoResponse;
	}
	
	@PostMapping(value="/removerCurso", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public CursoResponse removerCurso(@RequestBody(required=true) CursoDto cursoDto) {		
		CursoResponse cursoResponse = new CursoResponse();
		int removerCurso = cursoService.removerCurso(cursoDto);
		cursoResponse.setIdentificadorCursoBorrado(removerCurso);
		return cursoResponse;
	}
	
	@PostMapping(value="/actualizarCurso", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public CursoResponse actualizarCurso(@RequestBody(required=true) CursoDto cursoDto) {
		CursoResponse cursoResponse = new CursoResponse();
		CursoDto cursoDtoActualizado = cursoService.actualizarCurso(cursoDto);
		cursoResponse.setCursoCreadoActualizado(cursoDtoActualizado);
		return cursoResponse;		
	}
	
	
	/**
	 * Este medoto es para ser utilizado desde un front html
	 * @return lista de cursos
	 * http://localhost:8080/cursos/listaCursos
	 */
	@GetMapping("/listaCursos")
	public ModelAndView listaTodosLosCursosParaHtml(){
		ModelAndView modelAndView = new ModelAndView(VISTA_CURSOS);
		modelAndView.addObject("listaCursos",cursoService.listaTodosLosCursos());
		modelAndView.addObject("curso",new CursoDto());
		LOGGER.info("OBTENIENDO LA LISTA DE CURSOS");
		return modelAndView;
	}
	
	@PostMapping("/crearCursoParaHtml")
	public String crearCursoParaHtml(@ModelAttribute("curso") CursoDto cursoDto) {
		CursoDto crearCurso = cursoService.crearCurso(cursoDto);
		LOGGER.info("CURSO CREADO: "+crearCurso);
		return "redirect:/cursos/listaCursos";
	}
	
	
}
