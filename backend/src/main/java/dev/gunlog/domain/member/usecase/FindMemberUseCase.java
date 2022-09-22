package dev.gunlog.domain.member.usecase;

import dev.gunlog.domain.member.Member;
import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface FindMemberUseCase {

    Member findLoginId(String loginId) throws UserPrincipalNotFoundException;
}