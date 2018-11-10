package ufjf.wati.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason="Usuário ou senha inválidos.")
public class UnauthorizedUserException extends RuntimeException {

}
