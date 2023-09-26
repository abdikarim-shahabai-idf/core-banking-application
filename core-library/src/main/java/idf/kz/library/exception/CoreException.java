package idf.kz.library.exception;

import idf.kz.library.exception.message.MessageResponse;

public class CoreException extends RuntimeException {

  public CoreException(String message) {
    super(message);
  }

  public static CoreException reflectionApiException(String message) {
    return new CoreException(MessageResponse.REFLECTION_API_EXCEPTION.getDescription() + " " + message);
  }

  public static CoreException notFoundException(String message) {
    return new CoreException(MessageResponse.NOT_FOUND.getDescription() + " " + message);
  }

  public static CoreException impossiblePersistException(String message) {
    return new CoreException(MessageResponse.IMPOSSIBLE_PERSIST_OPERATION.getDescription() + " " + message);
  }

  public static CoreException queryParamRequired(String params) {
    return new CoreException(MessageResponse.QUERY_PARAM_REQUIRED.getDescription() + params);
  }

  public static CoreException incorrectParamException(String params) {
    return new CoreException(MessageResponse.INCORRECT_QUERY_PARAMS.getDescription() + params);
  }
}
