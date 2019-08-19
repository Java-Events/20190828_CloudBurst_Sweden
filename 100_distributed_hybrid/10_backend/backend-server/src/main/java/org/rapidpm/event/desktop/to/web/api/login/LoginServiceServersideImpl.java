package org.rapidpm.event.desktop.to.web.api.login;

import org.rapidpm.frp.model.Result;

import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.failure;
import static org.rapidpm.frp.model.Result.success;

public class LoginServiceServersideImpl
    implements LoginService {

//  public Optional<User> authenticate(String username, String password) {
  public List<SessionUser> authenticate(String username, String password) {
    final Result<SessionUser> result = match(matchCase(() -> failure("securityservice.login.denied")),
                                             matchCase(() -> username == null, () -> failure("securityservice.username.null")),
                                             matchCase(username::isEmpty, () -> failure("securityservice.username.is-empty")),
                                             matchCase(() -> password == null, () -> failure("securityservice.password.null")),
                                             matchCase(password::isEmpty, () -> failure("securityservice.password.is-empty")),
                                             matchCase(() -> username.equals("admin") && password.equals("admin"),
                                               () -> success(new SessionUser("Jon Doe", "Group A"))));

    return result.map(Collections::singletonList)
          .getOrElse(Collections::emptyList);


//    return (result.isPresent()) ? result.toOptional() : Optional.empty();
//    return (result.isPresent()) ? new ArrayList<>() : new ArrayList<>();
  }
}
