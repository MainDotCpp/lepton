package {{module.package}}.{{lowerCase biz}}.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface {{pascalCase biz}}Repository : JpaRepository<{{pascalCase biz}}, Long>, QuerydslPredicateExecutor<{{pascalCase biz}}> {}