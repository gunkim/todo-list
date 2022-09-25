package dev.gunlog.usecase.member;

import dev.gunlog.domain.member.Member;
import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface FindMemberUseCase {

    Member findLoginId(String loginId) throws UserPrincipalNotFoundException;
}