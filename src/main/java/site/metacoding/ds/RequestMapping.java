package site.metacoding.ds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})//메서드, 클래스, 필드(변수)를 이 어노테이션을 사용할 수 있는 종류를 강제함.
@Retention(RetentionPolicy.RUNTIME) //언제 실행될지 정하는 어노테이션. RUNTIM(런타임시), SOURCE(컴파일시)
public @interface RequestMapping {
	String value(); // value 메서드 고정, 키 값
}
