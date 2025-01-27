//package noidea.Dystopia.models;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
////import noidea.Dystopia.roles.Role;
//
//import java.util.Collection;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String password;
//    private boolean enabled;
//
//    @ManyToMany
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))
////    private Collection<Role> roles;
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public boolean isTokenExpired() {
//        return tokenExpired;
//    }
//
////    public Collection<Role> getRoles() {
////        return roles;
////    }
//
//    private boolean tokenExpired;
//
//
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    public void setTokenExpired(boolean tokenExpired) {
//        this.tokenExpired = tokenExpired;
//    }
//
////    public void setRoles(Collection<Role> roles) {
////        this.roles = roles;
////    }
//
//}
//
