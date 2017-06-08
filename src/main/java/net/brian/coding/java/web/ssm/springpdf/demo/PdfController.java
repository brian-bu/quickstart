package net.brian.coding.java.web.ssm.springpdf.demo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class PdfController {
	@RequestMapping("/business/applyFor.pdf")
	public ModelAndView text(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("helloworld", "HelloWorld");
		PdfPage view = new PdfPage();
		view.setAttributesMap(map);
		return new ModelAndView(view);
	}
}
