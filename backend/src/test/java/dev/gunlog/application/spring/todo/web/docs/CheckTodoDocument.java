package dev.gunlog.application.spring.todo.web.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.request.ParameterDescriptor;

public class CheckTodoDocument {
    public static RestDocumentationResultHandler check() {
        ParameterDescriptor[] pathParameter = new ParameterDescriptor[] {
            parameterWithName("id").description("할 일 ID")
        };

        return document("todos/check", pathParameters(pathParameter));
    }
}
