package by.gp.clinic.factory.predicateFactory;

import by.gp.clinic.dbo.QUserDbo;
import by.gp.clinic.search.UserSearchRequest;
import org.springframework.stereotype.Service;

@Service
public class UserPredicateFactory extends AbstractSearchRequestPredicateFactory<UserSearchRequest> {

    @Override
    void buildPredicates(final UserSearchRequest searchRequest) {
        addExpression(QUserDbo.userDbo.alias, searchRequest.getAlias());
        addExpression(QUserDbo.userDbo.role, searchRequest.getRole());
    }
}
