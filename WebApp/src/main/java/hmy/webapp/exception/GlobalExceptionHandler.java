package hmy.webapp.exception;

import hmy.webapp.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is used to handle exceptions that are thrown by the application.
 * It is annotated with @ControllerAdvice, which means that it will be used to handle exceptions across the whole application.
 * It contains a method annotated with @ExceptionHandler, which is used to handle a specific type of exception.
 * The method returns a ResponseEntity, which is used to return a JSON response with a status code.
 * @author Huang Miaoyan
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class) // 这个注解是用来设置需要捕获的异常类型
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 这个注解是用来设置返回的状态码
    public ResponseEntity<Response> handleException(BaseException e) {
        // Handle the exception and return a JSON response
        return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
