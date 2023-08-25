package kr.or.iei;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	//객체생성 어노테이면서 동시에 스프링 설정파일을 의미
@EnableWebMvc	//Spring Boot의 기본 설정을 내가 수정
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {//자원위치관련 설정을 건드리겠다는 의미
		//원래 기본설정 추가(html → /templates, 기본자원들 → /static폴더 사용
		registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/","classpath:/static/");
		//registry.addResourceHandler("/editor/**").addResourceLocations("file:///C:/Temp/upload/editor/");
		//registry.addResourceHandler("/event/**").addResourceLocations("file:///C:/Temp/upload/event/");
		registry.addResourceHandler("/event/**").addResourceLocations("file:///C:/Users/user1/Desktop/Semi_Project - ESSENTIAL#/semi_team_cereal/src/main/resources/static/img/event/");
		registry.addResourceHandler("/editor/**").addResourceLocations("file:///C:/Users/user1/Desktop/Semi_Project - ESSENTIAL#/semi_team_cereal/src/main/resources/static/img/editor/");
		registry.addResourceHandler("/product/**").addResourceLocations("file:///C:/Users/user1/Desktop/Semi_Project - ESSENTIAL#/semi_team_cereal/src/main/resources/static/img/product/");
	}//스프링부트 설정파일
	
	/*
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//레지스트리에 add한 순서대로 interceptor가 동작함!
		
		registry.addInterceptor(new LoginInterceptor())
		.addPathPatterns("/member/logout", "/member/mypage","/member/update","/member/delete","/member/admin","/member/changeLevel","/member/checkedChangeLevel", "/notice/**")
		.excludePathPatterns("/notice/list","/notice/view","/notice/filedown");
		;// /notice/** : notice의 모든 url에서 인터셉트하고 exclude~에 속한애들은 제외
		
		registry.addInterceptor(new AdminInterceptor())
		.addPathPatterns("/member/admin","/member/changeLevel","/member/checkedChangeLevel");
	}
	*/

}
