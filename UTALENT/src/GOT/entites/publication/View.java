package GOT.entites.publication;

public class View {
    private int id ;
    private int userId ;
    private int Publication_id ;

    public View() {
    }
    
    public View(int userId, int Publication_id) {
        this.userId = userId;
        this.Publication_id = Publication_id;
    }

    public View(int id, int userId, int Publication_id) {
        this.id = id;
        this.userId = userId;
        this.Publication_id = Publication_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPublication_id() {
        return Publication_id;
    }

    public void setPublication_id(int Publication_id) {
        this.Publication_id = Publication_id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final View other = (View) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Views{" + "id=" + id + ", userId=" + userId + ", Publication_id=" + Publication_id + '}';
    }
}
