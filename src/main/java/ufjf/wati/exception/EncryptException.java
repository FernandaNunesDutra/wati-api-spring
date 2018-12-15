package ufjf.wati.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason="Não foi possível validar os dados.")
public class EncryptException extends RuntimeException{

}
