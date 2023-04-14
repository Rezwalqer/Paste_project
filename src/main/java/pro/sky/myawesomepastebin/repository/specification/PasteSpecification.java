package pro.sky.myawesomepastebin.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import pro.sky.myawesomepastebin.model.Paste;

public class PasteSpecification {
    public static Specification<Paste> byTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(title)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("title"), title);
        };
    }
    public static Specification<Paste> byBody(String body) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(body)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("body"), body);
        };
    }
}
