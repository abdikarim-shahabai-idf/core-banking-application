package idf.kz.library.exception.message;

public enum MessageResponse {
  NOT_FOUND("not found"),
  INCORRECT_FIELDS("fields must not be empty or null"),
  INCORRECT_QUERY_PARAMS("incorrect query params:"),
  REFLECTION_API_EXCEPTION("reflection api exception"),
  IMPOSSIBLE_PERSIST_OPERATION("impossible persist operation"),
  QUERY_PARAM_REQUIRED("required query params ");

  private final String description;

  MessageResponse(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
