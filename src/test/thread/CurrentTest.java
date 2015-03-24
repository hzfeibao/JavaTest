package test.thread;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CurrentTest {
	
}

class UnsafeGuestbookServlet extends HttpServlet {

    //private Set visitorSet = new HashSet();//HashSet是线程不安全的
    private Set visitorSet = Collections.synchronizedSet(new HashSet());//线程安全
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String visitorName = httpServletRequest.getParameter("NAME");
        if (visitorName != null)
            visitorSet.add(visitorName);
    }

}