package dev.gunlog.application.spring.todo.web.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.request.ParameterDescriptor;

public class DeleteTodoDocument {
    public static RestDocumentationResultHandler delete() {
        ParameterDescriptor[] pathParameter = new ParameterDescriptor[] {
            parameterWithName("id").description("할 일 ID")
        };

        return document("todos/delete", pathParameters(pathParameter));
    }
}
