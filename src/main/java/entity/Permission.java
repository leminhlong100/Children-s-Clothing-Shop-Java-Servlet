package entity;

public class Permission {
    private Long id;
    private Role role;
    private int idRole;
    private Resource resource;
    private int idResource;
    private String action;

    public Permission() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Permission(Long id, Role role, int idRole, Resource resource, int idResource, String action) {
        this.id = id;
        this.role = role;
        this.idRole = idRole;
        this.resource = resource;
        this.idResource = idResource;
        this.action = action;
    }

    public Permission(Long id, Role role, Resource resource, String action) {
        this.id = id;
        this.role = role;
        this.resource = resource;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getIdRole() {
        return idRole;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getIdResource() {
        return idResource;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", role=" + role +
                ", idRole=" + idRole +
                ", resource=" + resource +
                ", idResource=" + idResource +
                ", action='" + action + '\'' +
                '}';
    }
}
