package time_tracking.model;

public abstract class Entity_With_Id {
    private long id;

    public Entity_With_Id() {
    }

    public Entity_With_Id(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity_With_Id that = (Entity_With_Id) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
