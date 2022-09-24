package dev.gunlog.application.spring.todo.web.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class CreateTodoDocument {

    public static RestDocumentationResultHandler create() {
        FieldDescriptor[] request = new FieldDescriptor[]{
            fieldWithPath("text").type(JsonFieldType.STRING).description("할 일 내용"),
            fieldWithPath("check").type(JsonFieldType.BOOLEAN).description("할 일 체크 여부")};

        return document("todos/create", requestFields(request));
    }
}
