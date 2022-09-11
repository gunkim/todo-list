package dev.gunlog.domain.member;

import java.time.LocalDateTime;
import java.util.Objects;

public record Member(
    Long id,
    String loginId,
    String password,
    String name,
    Role role,
    LocalDateTime createdDate,
    LocalDateTime updatedDate
) {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return id.equals(member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
