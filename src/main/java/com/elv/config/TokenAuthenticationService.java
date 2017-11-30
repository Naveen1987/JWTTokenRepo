package com.elv.config;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security
            .authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.elv.models.TokenHistory;
import com.elv.repo.TokenHistoryRepo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import static java.util.Collections.emptyList;

import java.util.Calendar;

@Component
public class TokenAuthenticationService {
  //static final long EXPIRATIONTIME = 864_000_000; // 10 days
  static final long EXPIRATIONTIME = 86400000; // 1 days
  static final String SECRET = "ThisIsASecret";
  static final String TOKEN_PREFIX = "Bearer";
  static final String HEADER_STRING = "Authorization";
  public String addAuthentication(HttpServletResponse res, String username) {
    String JWT = Jwts.builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .setIssuedAt(new Date())
        .compact();
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    return TOKEN_PREFIX + " " + JWT;
  }

  public  Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);    
    if (token != null) {
      // parse the token.
      String user = Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
          .getBody()
          .getSubject();
      return user != null ?
          new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
          null;
    }
    return null;
  }
  
  public boolean invalidToken(HttpServletRequest request) {
		    ServletContext servletContext = request.getServletContext();
	        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	        TokenHistoryRepo th = webApplicationContext.getBean(TokenHistoryRepo.class);
	        String token =request.getHeader(HEADER_STRING);
	        //TokenHistory t=th.findByTokenValue(token);
	        TokenHistory t=th.findOne(token);
	        if(t==null) {
	        	return true;
	        }
			return false; 
	}
  
  public boolean saveToken(HttpServletRequest request,String token) {
		 ServletContext servletContext = request.getServletContext();
	        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	        TokenHistoryRepo tokenHistoryRepo = webApplicationContext.getBean(TokenHistoryRepo.class);
	        //String token =request.getHeader(HEADER_STRING);
	        TokenHistory t=new TokenHistory();
			t.setTokenValue(token);
			t.setExpireOn(Calendar.getInstance());
			t=tokenHistoryRepo.save(t);
			if(t!=null) {
				return true;
			}
			return false;
	}
  public boolean revokeToken(HttpServletRequest request) {
		 ServletContext servletContext = request.getServletContext();
	        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	        TokenHistoryRepo tokenHistoryRepo = webApplicationContext.getBean(TokenHistoryRepo.class);
	        String token =request.getHeader(HEADER_STRING);
	       /* TokenHistory t=tokenHistoryRepo.findByTokenValue(token);
	        tokenHistoryRepo.delete(t);*/
	        //tokenHistoryRepo.deleteToken(token);
	        tokenHistoryRepo.delete(token);
	       return true;
	}
}