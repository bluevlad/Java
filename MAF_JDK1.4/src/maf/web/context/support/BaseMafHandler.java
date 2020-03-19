package maf.web.context.support;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseMafHandler implements MafHandlerInterface {
	
	public void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		this.process(request, response);
	}
	
	protected abstract void process(HttpServletRequest request, HttpServletResponse response) throws ServletException;
}
