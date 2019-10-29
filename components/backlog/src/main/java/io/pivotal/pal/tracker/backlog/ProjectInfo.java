package io.pivotal.pal.tracker.backlog;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("ProjectInfo")
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = -8243145428016231L;

    @Id
    private Long id;
    public final boolean active;

    private ProjectInfo() {
        this(false);
    }

    public ProjectInfo(boolean active) {
        this.active = active;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectInfo that = (ProjectInfo) o;

        return active == that.active;
    }

    @Override
    public int hashCode() {
        return (active ? 1 : 0);
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
            "active=" + active +
            '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
