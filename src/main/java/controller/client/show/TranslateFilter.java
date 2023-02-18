package controller.client.show;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class TranslateFilter extends HttpFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest rep = (HttpServletRequest) request;
		String lang = request.getParameter("lang");
		if(lang==null) {
			lang = "vi_VN";
		}
		if(lang!=null) {
			rep.getSession().setAttribute("LANG", lang);	
		}
		chain.doFilter(request, response);
	}
}
