package kals.com.core.specification;


import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonSpecification<T> implements Specification<T> {

    private final String query;
    private final Map<String, Integer> precedenceMap = Map.of("OR", 1, "AND", 2);

    public CommonSpecification(String query) {
        this.query = query;
    }

    @Override
    public @Nullable Predicate toPredicate(Root<T> root, CriteriaQuery<?> cbQuery, CriteriaBuilder criteriaBuilder) {

        if (query == null) {
            return null;
        }

        List<String> splitTokens = formParts();
        Stack<Predicate> predicateStack = new Stack<>();

        for (String chunk : splitTokens) {
            if (chunk.equalsIgnoreCase("AND")) {
                Predicate right = predicateStack.pop();
                Predicate left = predicateStack.pop();
                predicateStack.push(criteriaBuilder.and(left, right));
            } else if (chunk.equalsIgnoreCase("OR")) {
                Predicate right = predicateStack.pop();
                Predicate left = predicateStack.pop();
                predicateStack.push(criteriaBuilder.or(left, right));
            } else {
                predicateStack.push(buildQuery(root, criteriaBuilder, chunk));
            }
        }

        return predicateStack.isEmpty() ? null : predicateStack.pop();
    }

    private List<String> formParts() {
        List<String> output = new ArrayList<>();
        Stack<String> operatorsStack = new Stack<>();

        //to match regex
        Pattern tokenPattern = Pattern.compile("\\(|\\)|AND|OR|[\\w\\.]+:\"[^\"]*\"|[\\w\\.]+:[^\\s()]+");
        Matcher matcher = tokenPattern.matcher(query);

        List<String> tokens = new ArrayList<>();

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        for (String chunk : tokens) {
            if (chunk.equals("(")) {
                operatorsStack.push(chunk);
            } else if (chunk.equals(")")) {
                while (!operatorsStack.isEmpty() && !operatorsStack.peek().equals("(")) {
                    output.add(operatorsStack.pop());
                }
                if (!operatorsStack.isEmpty() && operatorsStack.peek().equals("(")) {
                    operatorsStack.pop();
                }
            } else if (chunk.equalsIgnoreCase("AND") || chunk.equalsIgnoreCase("OR")) {
                while (!operatorsStack.isEmpty() && precedenceMap.get(chunk.toUpperCase()) <= precedenceMap.getOrDefault(operatorsStack.peek().toUpperCase(), 0)) {
                    output.add(operatorsStack.pop());
                }
                operatorsStack.push(chunk);
            } else {
                output.add(chunk);
            }
        }

        while (!operatorsStack.isEmpty()) {
            output.add(operatorsStack.pop());
        }

        return output;
    }


    private Predicate buildQuery(Root<T> root, CriteriaBuilder criteriaBuilder, String chunk) {

        if (chunk.contains("!::")) {
            String[] splitString = chunk.split("!::");
            String fieldName = splitString[0];
            String value = splitString[1].replaceAll("^\"|\"$", "");
            return criteriaBuilder.not(getRootPath(root, fieldName).in(Arrays.asList(value.split(","))));
        } else if (chunk.contains("::")) {
            String[] splitString = chunk.split("::");
            String fieldName = splitString[0];
            String value = splitString[1].replaceAll("^\"|\"$", "");
            return getRootPath(root, fieldName).in(Arrays.asList(value.split(" ")));
        } else if (chunk.contains("!:")) {
            String[] splitString = chunk.split("!:");
            String fieldName = splitString[0];
            String value = splitString[1].replaceAll("^\"|\"$", "");
            return criteriaBuilder.notEqual(getRootPath(root, fieldName), value);
        } else if (chunk.contains(":")) {
            String[] splitString = chunk.split(":");
            String fieldName = splitString[0];
            String value = splitString[1].replaceAll("^\"|\"$", "");
            Class<?> fieldType = root.get(fieldName).getJavaType();
            boolean isEnum = Enum.class.isAssignableFrom(fieldType);
            boolean isBoolean = fieldType.equals(Boolean.class);
            if (isEnum) {
                return criteriaBuilder.equal(getRootPath(root, fieldName), Enum.valueOf((Class<Enum>) fieldType, value.toUpperCase()));
            } else if (isBoolean) {
                return criteriaBuilder.equal(getRootPath(root, fieldName), Boolean.valueOf(value));
            }

            return criteriaBuilder.equal(getRootPath(root, fieldName), value);
        }
        return null;

    }


    private Path<?> getRootPath(Root<T> root, String fieldName) {
        if (fieldName.contains(".")) {
            String[] parts = fieldName.split("\\.");
            From<?, ?> joinRoot = root;

            for (int i = 0; i < parts.length - 1; i++) {
                joinRoot = joinRoot.join(parts[i], JoinType.LEFT);
            }
            return joinRoot.get(parts[parts.length - 1]);
        } else {
            return root.get(fieldName);
        }
    }

}